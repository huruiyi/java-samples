package org.example;

import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngines;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestActiviti {

  @Test
  public void deploy() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("processes/LeaveProcess.bpmn20.xml")
        .name("请求单流程")
        .category("办公类别")
        .deploy();

    log.info("部署的id:{}", deploy.getId());
    log.info("部署的名称：{}", deploy.getName());
  }

  //执行流程
  @Test
  public void startProcess() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    String processKey = "leaveBill";
    //取运行时服务
    RuntimeService runtimeService = processEngine.getRuntimeService();
    //取得流程实例
    ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey);//通过流程定义的key 来执行流程
    log.info("流程实例id:{}", pi.getId());
    log.info("流程定义id:{}", pi.getProcessDefinitionId());
  }

  //查询任务
  @Test
  public void queryTask() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //任务的办理人：张三-》李四-》王五
    String assignee = "王五";
    //取得任务服务
    TaskService taskService = processEngine.getTaskService();
    //创建一个任务查询对象
    TaskQuery taskQuery = taskService.createTaskQuery();
    //办理人的任务列表
    List<Task> list = taskQuery.taskAssignee(assignee).list();

    //遍历任务列表
    if (list != null && !list.isEmpty()) {
      for (Task task : list) {
        log.info("任务的办理人：{}", task.getAssignee());
        log.info("任务的id：{}", task.getId());
        log.info("任务的名称：{}", task.getName());
      }
    }
  }

  //完成任务
  @Test
  public void compileTask() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    processEngine.getTaskService().complete("25002");
    log.info("当前任务执行完毕");
  }

}
