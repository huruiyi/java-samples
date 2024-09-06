package org.example;

import java.util.List;
import org.activiti.engine.ProcessEngine;
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

public class Main {

  public static void main(String[] args) {
//    ProcessEngineConfiguration pec = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
//    ProcessEngine pe = pec.buildProcessEngine();
    test1();
  }

  static void test1() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RuntimeService runtimeService = processEngine.getRuntimeService();
    TaskService taskService = processEngine.getTaskService();
    RepositoryService repositoryService = processEngine.getRepositoryService();

    //act_re_deployment
    Deployment deployment = repositoryService.createDeployment()
        .addClasspathResource("请假.bpmn20.xml")
        .name("请假申请单流程")
        .key("holiday")
        .deploy();

    System.out.println(deployment.getName());
    System.out.println(deployment.getId());
    System.out.println(deployment.getKey());

    //act_re_procdef
    runtimeService.startProcessInstanceById("请假:10:22504");
    System.out.println(taskService.createTaskQuery().count());

    Task firstTask = taskService.createTaskQuery().taskAssignee("xxa").singleResult();
    taskService.complete(firstTask.getId());

  }


  @Test
  void test3() {
    //1.得到ProcessEngine对象
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();

    repositoryService.createDeployment()
        .addClasspathResource("请假.bpmn20.xml")
        .name("请假申请单流程")
        .key("holiday")
        .deploy();

    //2.得到RunService对象
    RuntimeService runtimeService = processEngine.getRuntimeService();

    //3.创建流程实例  流程定义的key需要知道 holiday
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("请假");

    //4.输出实例的相关信息
    System.out.println("流程部署ID：" + processInstance.getDeploymentId());
    System.out.println("流程定义ID：" + processInstance.getProcessDefinitionId());
    System.out.println("流程实例ID：" + processInstance.getId());
    System.out.println("活动ID：" + processInstance.getActivityId());

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

      System.out.println("------------------------");
      System.out.println(" 流 程 部 署 id ： " + processDefinition.getDeploymentId());
      System.out.println("流程定义id： " + processDefinition.getId());
      System.out.println("流程定义名称： " + processDefinition.getName());
      System.out.println("流程定义key： " + processDefinition.getKey());
      System.out.println("流程定义版本： " + processDefinition.getVersion());
    }
  }

  /**
   * 删除指定流程id的流程
   */
  @Test
  public void test5() {
    // 流程部署id
    String deploymentId = "2501";
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
