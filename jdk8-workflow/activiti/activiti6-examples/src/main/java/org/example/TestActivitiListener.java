package org.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestActivitiListener {

  ProcessEngine processEngine;
  RuntimeService runtimeService;

  @BeforeEach
  public void init() {
    processEngine = ProcessEngines.getDefaultProcessEngine();
    runtimeService = processEngine.getRuntimeService();
  }

  @Test
  public void deploy() {
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("processes/test-listener.bpmn20.xml")
        .name("监听测试")
        .category("办公类别")
        .deploy();
    log.info("部署的id:{}", deploy.getId());
    log.info("部署的名称：{}", deploy.getName());
    log.info("部署的分类：{}", deploy.getCategory());
  }

  //执行流程
  @Test
  public void startProcess() {
    String processKey = "testListenerId";
    ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey);
    log.info("流程实例id:{}", pi.getId());
    log.info("流程实例名称:{}", pi.getName());
    log.info("流程定义id:{}", pi.getProcessDefinitionId());
    log.info("流程定义key:{}", pi.getProcessDefinitionKey());
    log.info("流程定义name:{}", pi.getProcessDefinitionName());
    log.info("流程定义version:{}", pi.getProcessDefinitionVersion());
  }


}
