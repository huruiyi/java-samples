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
  public void test1() {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    RuntimeService runtimeService = engine.getRuntimeService();
    TaskService taskService = engine.getTaskService();

    repositoryService.createDeployment().addClasspathResource("processes/test01.bpmn20.xml").deploy();
    runtimeService.startProcessInstanceByKey("LeaveApplyProcess");
    log.info("启动流程...");
    log.info("流程个数：{}", taskService.createTaskQuery().count());

    Task firstTask = taskService.createTaskQuery().taskAssignee("liuxiaopeng").singleResult();
    taskService.complete(firstTask.getId());
    log.info("用户任务{}办理完成，办理人为：{}", firstTask.getName(), firstTask.getAssignee());
    log.info("流程个数：{}", taskService.createTaskQuery().count());

    Task secondTask = taskService.createTaskQuery().taskAssignee("hebo").singleResult();
    log.info("用户任务{}办理完成，办理人为：{}", secondTask.getName(), secondTask.getAssignee());
    taskService.complete(secondTask.getId());

    log.info("流程结束后，剩余任务个数：{}", taskService.createTaskQuery().count());

    engine.close();
  }


  @Test
  void test2_1() {
    ProcessEngineConfiguration configuration =
        ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
    ProcessEngine processEngine = configuration.buildProcessEngine();

    TaskService taskService = processEngine.getTaskService();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();

    Deployment deployment = repositoryService
        .createDeployment()
        .addClasspathResource("processes/test02.bpmn20.xml")
        .deploy();

    ProcessDefinition processDefinition = repositoryService
        .createProcessDefinitionQuery()
        .deploymentId(deployment.getId())
        .singleResult();

    ProcessInstance processInstance = runtimeService.startProcessInstanceById(processDefinition.getId());
    Task task = taskService.createTaskQuery()
        .processInstanceId(processInstance.getId())
        .singleResult();
    taskService.complete(task.getId());
  }

  @Test
  void test2_2() {
    ProcessEngineConfiguration configuration =
        ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
    ProcessEngine processEngine = configuration.buildProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();
    TaskService taskService = processEngine.getTaskService();
    RuntimeService runtimeService = processEngine.getRuntimeService();

    Deployment deployment = repositoryService
        .createDeployment()
        .addClasspathResource("processes/test02.bpmn20.xml")
        .deploy();

    ProcessDefinition processDefinition = repositoryService
        .createProcessDefinitionQuery()
        .deploymentId(deployment.getId())
        .singleResult();

    ProcessInstance processInstance = runtimeService
        .startProcessInstanceByKey(processDefinition.getKey());
    System.out.println("任务个数：" + taskService.createTaskQuery().count());

    Task task = taskService.createTaskQuery()
        .processInstanceId(processInstance.getId())
        .singleResult();
    taskService.complete(task.getId());
    System.out.println("任务个数：" + taskService.createTaskQuery().count());
  }

  @Test
  void test3_1() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RuntimeService runtimeService = processEngine.getRuntimeService();
    TaskService taskService = processEngine.getTaskService();
    RepositoryService repositoryService = processEngine.getRepositoryService();

    //act_re_deployment
    Deployment deployment = repositoryService
        .createDeployment()
        .addClasspathResource("processes/请假.bpmn20.xml")
        .name("请假申请单流程")
        .key("holiday")
        .deploy();

    ProcessDefinition processDefinition = repositoryService
        .createProcessDefinitionQuery()
        .deploymentId(deployment.getId())
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
  void test3_2() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();

    repositoryService.createDeployment()
        .addClasspathResource("processes/请假.bpmn20.xml")
        .name("请假申请单流程")
        .key("holiday")
        .deploy();

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
      log.info("----------------------------------------------------------------------");
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
