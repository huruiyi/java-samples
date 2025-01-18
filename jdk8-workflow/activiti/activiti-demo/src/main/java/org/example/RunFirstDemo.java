package org.example;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.jupiter.api.Test;

@Slf4j
public class RunFirstDemo {

  @Test
  public void simple() {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    RuntimeService runtimeService = engine.getRuntimeService();
    TaskService taskService = engine.getTaskService();

    repositoryService.createDeployment().addClasspathResource("processes/first.bpmn20.xml").deploy();
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
  public void createdInMemProcessEngine() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
    log.info("ProcessEngineConfiguration:{}", configuration);
    ProcessEngine processEngine = configuration.buildProcessEngine();
    log.info("ProcessEngine:{}", processEngine);
  }


  @Test
  public void createProcessEngineByBuildProcessEngineTest() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
    log.info("ProcessEngineConfiguration:{}", configuration);

    ProcessEngine processEngine = configuration.buildProcessEngine();
    log.info("ProcessEngine:{}", processEngine);
  }

  @Test
  public void testCreateFromInputStream() throws Exception {
    File file = new File("src/main/resources/activiti.cfg2.xml");
    // 获取文件输入流
    InputStream inputStream = Files.newInputStream(file.toPath());
    ProcessEngineConfiguration configuration =
        ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream, "processEngineConfiguration");
    log.info("ProcessEngineConfiguration:{}", configuration);
  }

  @Test
  public void testCreateFromResource() {
    ProcessEngineConfiguration configuration =
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg2.xml", "processEngineConfiguration");
    log.info("ProcessEngineConfiguration:{}", configuration);
  }


  @Test
  public void testCreateFromStandalone() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    configuration.setJdbcDriver("com.mysql.jdbc.Driver");
    configuration.setJdbcUrl("jdbc:mysql://localhost:3306/workflow");
    configuration.setJdbcUsername("root");
    configuration.setJdbcPassword("fairy0619Xyz!");
    configuration.setDatabaseSchemaUpdate("true");

    log.info("ProcessEngineConfiguration:{}", configuration);
  }


  @Test
  public void createProcessEngineByInitTest() {
    //读取activiti.cfg.xml配置文件，创建工作流引擎对象缓存到Map中
    ProcessEngines.init();
    //获取Map
    Map<String, ProcessEngine> enginesMap = ProcessEngines.getProcessEngines();
    //获取key为default的对象
    ProcessEngine processEngine = enginesMap.get("default");
    System.out.println(processEngine);
  }


  @Test
  public void defaultProcessEngine() {
    ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
    System.out.println(defaultProcessEngine);
  }

  @Test
  public void defaultProcessEngineFromResource() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }


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
