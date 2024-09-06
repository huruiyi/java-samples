package com.bpm.example.demo1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.task.Task;


@Slf4j
public class RunFirstDemo {

  public static void main(String[] args) {
    ProcessEngine engine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = engine.getRepositoryService();
    RuntimeService runtimeService = engine.getRuntimeService();
    TaskService taskService = engine.getTaskService();

    repositoryService.createDeployment().addClasspathResource("processes/LeaveApplyProcess.bpmn").deploy();
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

  public void createProcessEngineByBuildProcessEngineTest() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }

  public void testCreateFromResourceDefault() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
    System.out.println(configuration);
  }


  public void testCreateFromInputStream() throws FileNotFoundException {
    File file = new File("resource/my-config.xml");
    // 获取文件输入流
    InputStream fis = new FileInputStream(file);
    //使用createProcessEngineConfigurationFromInputStream()方法创建
    //ProcessEngineConfiguration
    ProcessEngineConfiguration processEngineConfigurationFromInputStream = ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(
        fis, "myProcessEngineConfiguration");
    System.out.println(processEngineConfigurationFromInputStream);
  }


  public void testCreateFromResource() {
    ProcessEngineConfiguration processEngineConfigurationFromResource = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource(
        "resource/my-config.xml", "myProcessEngineConfiguration");
    System.out.println(processEngineConfigurationFromResource);
  }


  public void testCreateFromStandalone() {
    ProcessEngineConfiguration processEngineConfigurationFromStandalone = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    processEngineConfigurationFromStandalone.setJdbcDriver("com.mysql.jdbc.Driver");
    processEngineConfigurationFromStandalone.setJdbcUrl("jdbc:mysql://localhost:3306/workflow");
    processEngineConfigurationFromStandalone.setJdbcUsername("root");
    processEngineConfigurationFromStandalone.setJdbcPassword("root");
    processEngineConfigurationFromStandalone.setDatabaseSchemaUpdate("true");
    System.out.println(processEngineConfigurationFromStandalone);
  }

  public void createProcessEngineByInitTest() {
    //读取activiti.cfg.xml配置文件，创建工作流引擎对象缓存到Map中
    ProcessEngines.init();
    //获取Map
    Map<String, ProcessEngine> enginesMap = ProcessEngines.getProcessEngines();
    //获取key为default的对象
    ProcessEngine processEngine = enginesMap.get("default");
    System.out.println(processEngine);
  }


  public void createProcessEngineByGetDefaultProcessEngineTest1() {
    ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
    System.out.println(defaultProcessEngine);
  }

  public void createProcessEngineByGetDefaultProcessEngineTest2() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }


}
