package org.example;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.identity.Authentication;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestClaim {

  ProcessEngine processEngine;
  RuntimeService runtimeService;
  TaskService taskService;

  @BeforeEach
  public void init() {
    processEngine = ProcessEngines.getDefaultProcessEngine();
    runtimeService = processEngine.getRuntimeService();
    taskService = processEngine.getTaskService();
  }

  @Test
  public void deploy() {
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("processes/permission-apply.bpmn20.xml")
        .name("监听测试")
        .category("办公类别")
        .deploy();
    log.info("部署的id:{}", deploy.getId());
    log.info("部署的名称：{}", deploy.getName());
    log.info("部署的分类：{}", deploy.getCategory());
  }

  @Test
  public void startProcess() {
    Authentication.setAuthenticatedUserId("admin");
    String processKey = "permissionApply";
    ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey);
    log.info("流程实例id:{}", pi.getId());
    log.info("流程实例名称:{}", pi.getName());
    log.info("流程定义id:{}", pi.getProcessDefinitionId());
    log.info("流程定义key:{}", pi.getProcessDefinitionKey());
    log.info("流程定义name:{}", pi.getProcessDefinitionName());
    log.info("流程定义version:{}", pi.getProcessDefinitionVersion());
  }

  @Test
  public void showTask() {
    taskService.createTaskQuery().list().forEach(task -> {
      log.info("任务id:{}", task.getId());
      log.info("任务名称:{}", task.getName());
      log.info("任务创建时间:{}", task.getCreateTime());
      log.info("任务办理人:{}", task.getAssignee());
      log.info("任务定义id:{}", task.getTaskDefinitionKey());
      log.info("流程实例id:{}", task.getProcessInstanceId());
    });
  }

  @Test
  void completeTask() {
    taskService.complete("17502");
  }

  @Test
  void xx() {
    List<IdentityLink> candidates = taskService.getIdentityLinksForTask("17502");
    candidates.stream().forEach(identityLink -> {
      log.info("用户id:{}", identityLink.getUserId());
    });

    //taskService.claim(taskId, userId);
    //taskService.unclaim(taskId);

  }


}
