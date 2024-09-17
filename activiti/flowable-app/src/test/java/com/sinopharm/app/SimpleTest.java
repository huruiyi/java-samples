package com.sinopharm.app;

import jakarta.annotation.Resource;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = AppStart.class)
public class SimpleTest {

  @Resource
  private ProcessEngine processEngine;

  @Test
  public void deploy() {
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = repositoryService.createDeployment()
        .addClasspathResource("processes/test-ask-leave.bpmn20.xml").deploy();
    System.out.println("流程部署ID：" + deployment.getId());
  }

  @Test
  public void complete() {
    RuntimeService runtimeService = processEngine.getRuntimeService();
    //act_re_procdef.KEY_
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("test-ask-leave");
    System.out.println("pi：" + processInstance.getId());

    TaskService taskService = processEngine.getTaskService();
    Task task = taskService.createTaskQuery()
        //act_ru_task.PROC_INST_ID_
        .processInstanceId(processInstance.getId()).singleResult();
    taskService.complete(task.getId());
  }

}
