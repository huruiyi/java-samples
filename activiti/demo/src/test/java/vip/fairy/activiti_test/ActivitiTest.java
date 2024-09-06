package vip.fairy.activiti_test;

import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

public class ActivitiTest {


  @Test
  void test1() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
    ProcessEngine processEngine = configuration.buildProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = processEngine.getRepositoryService().createDeployment()
        .addClasspathResource("processes/test.bpmn20.xml").deploy();

    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
    TaskService taskService = processEngine.getTaskService();

    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceById(processDefinition.getId());
    Task task1 = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task1.getId());


  }

  @Test
  void test2() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
    System.out.println(configuration);
    ProcessEngine processEngine = configuration.buildProcessEngine();

    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = processEngine.getRepositoryService().createDeployment()
        .addClasspathResource("processes/test.bpmn20.xml").deploy();

    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
    TaskService taskService = processEngine.getTaskService();

    ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey(processDefinition.getKey());
    System.out.println("任务个数：" + taskService.createTaskQuery().count());

    Task task = taskService.createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task.getId());
    System.out.println("任务个数：" + taskService.createTaskQuery().count());
  }
}
