package org.example;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.repository.ProcessDefinitionQuery;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

@Slf4j
public class ActivitiSimple {

  @Test
  void test0() {
    //取得ProcessEngineConfiguration对象
    ProcessEngineConfiguration engineConfiguration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    //设置数据库连接属性
    engineConfiguration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
    engineConfiguration.setJdbcUrl(
        "jdbc:mysql://localhost:3306/activiti6?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true");
    engineConfiguration.setJdbcUsername("root");
    engineConfiguration.setJdbcPassword("fairy0619Xyz!");
    //  设置创建表的策略 （当没有表时，自动创建表）
    //  DB_SCHEMA_UPDATE_FALSE = "false";//不会自动创建表，没有表，则抛异常
    //  DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";//先删除，再创建表
    //  DB_SCHEMA_UPDATE_TRUE = "true";//假如没有表，则自动创建
    engineConfiguration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    //通过ProcessEngineConfiguration对象创建 ProcessEngine 对象
    ProcessEngine processEngine = engineConfiguration.buildProcessEngine();
    System.out.println(processEngine);
  }

  @Test
  void test1() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }

  @Test
  void test2() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RuntimeService runtimeService = processEngine.getRuntimeService();
    TaskService taskService = processEngine.getTaskService();
    RepositoryService repositoryService = processEngine.getRepositoryService();

    //act_re_deployment
    Deployment deployment = repositoryService.createDeployment()
        .addClasspathResource("processes/请假.bpmn20.xml")
        .name("请假申请单流程")
        .key("holiday")
        .deploy();

    ProcessDefinition processDefinition = processEngine.getRepositoryService().createProcessDefinitionQuery().deploymentId(deployment.getId())
        .singleResult();

    log.info(deployment.getName());
    log.info(deployment.getId());
    log.info(deployment.getKey());

    //act_re_procdef
    runtimeService.startProcessInstanceById(processDefinition.getId());
    System.out.println(taskService.createTaskQuery().count());

    Task firstTask = taskService.createTaskQuery().taskAssignee("xxa").singleResult();
    taskService.complete(firstTask.getId());
    System.out.println(taskService.createTaskQuery().count());
  }


  @Test
  void test3() {
    //1.得到ProcessEngine对象
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();

    repositoryService.createDeployment()
        .addClasspathResource("processes/请假.bpmn20.xml")
        .name("请假申请单流程")
        .key("holiday")
        .deploy();

    //2.得到RunService对象
    RuntimeService runtimeService = processEngine.getRuntimeService();

    //3.创建流程实例  流程定义的key需要知道 holiday
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("请假");

    //4.输出实例的相关信息
    log.info("流程部署ID：{}", processInstance.getDeploymentId());
    log.info("流程定义ID：{}", processInstance.getProcessDefinitionId());
    log.info("流程实例ID：{}", processInstance.getId());
    log.info("活动ID：{}", processInstance.getActivityId());
  }

  @Test
  public void test4() {
    // 流程定义key
    String processDefinitionKey = "请假";
    //1.得到ProcessEngine对象
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    // 获取repositoryService
    RepositoryService repositoryService = processEngine.getRepositoryService();
    // 查询流程定义
    ProcessDefinitionQuery processDefinitionQuery = repositoryService.createProcessDefinitionQuery();
    //遍历查询结果
    List<ProcessDefinition> list = processDefinitionQuery
        .processDefinitionKey(processDefinitionKey)
        .orderByProcessDefinitionVersion().desc().list();

    for (ProcessDefinition processDefinition : list) {

      log.info("------------------------");
      log.info(" 流 程 部 署 id ： {}", processDefinition.getDeploymentId());
      log.info("流程定义id： {}", processDefinition.getId());
      log.info("流程定义名称： {}", processDefinition.getName());
      log.info("流程定义key： {}", processDefinition.getKey());
      log.info("流程定义版本： {}", processDefinition.getVersion());
    }
  }

  /**
   * 删除指定流程id的流程
   */
  @Test
  public void test5() {
    // 流程部署id:act_re_procdef.DEPLOYMENT_ID_
    String deploymentId = "13";
    //1.得到ProcessEngine对象
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    // 通过流程引擎获取repositoryService
    RepositoryService repositoryService = processEngine.getRepositoryService();
    //删除流程定义， 如果该流程定义已有流程实例启动则删除时出错
    // repositoryService.deleteDeployment(deploymentId);
    //设置true 级联删除流程定义，即使该流程有流程实例启动也可以删除，设
    //置为false非级别删除方式，如果流程
    repositoryService.deleteDeployment(deploymentId, true);
  }


}
