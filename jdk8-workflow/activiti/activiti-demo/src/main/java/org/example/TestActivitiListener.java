package org.example;

import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestActivitiListener {

  @Test
  public void deploy() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("processes/test03.bpmn20.xml")
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
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    String processKey = "testListenerId";
    //取运行时服务
    RuntimeService runtimeService = processEngine.getRuntimeService();
    //取得流程实例
    ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey);//通过流程定义的key 来执行流程
    log.info("流程实例id:{}", pi.getId());
    log.info("流程实例名称:{}", pi.getName());
    log.info("流程定义id:{}", pi.getProcessDefinitionId());
    log.info("流程定义key:{}", pi.getProcessDefinitionKey());
    log.info("流程定义name:{}", pi.getProcessDefinitionName());
    log.info("流程定义version:{}", pi.getProcessDefinitionVersion());
  }


}
