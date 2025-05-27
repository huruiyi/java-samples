package vip.fairy;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.*;
import org.activiti.engine.impl.RepositoryServiceImpl;
import org.activiti.engine.impl.persistence.entity.ExecutionEntity;
import org.activiti.engine.impl.persistence.entity.ProcessDefinitionEntity;
import org.activiti.engine.impl.pvm.PvmActivity;
import org.activiti.engine.impl.pvm.PvmTransition;
import org.activiti.engine.impl.pvm.process.ActivityImpl;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskQuery;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestActiviti0328 {

  @Test
  public void deploy() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deploy = repositoryService.createDeployment()
        .addClasspathResource("processes/LeaveProcess0328.bpmn20.xml")
        .name("请求单流程")
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
    String processKey = "LeaveProcess0328";
    //取运行时服务
    RuntimeService runtimeService = processEngine.getRuntimeService();
    //取得流程实例
    HashMap<String, Object> variables = new HashMap<>();
    variables.put("employeeName", "张三");
    variables.put("day", "10");
    ProcessInstance pi = runtimeService.startProcessInstanceByKey(processKey, variables);//通过流程定义的key 来执行流程
    log.info("流程实例id:{}", pi.getId());
    log.info("流程实例名称:{}", pi.getName());
    log.info("流程定义id:{}", pi.getProcessDefinitionId());
    log.info("流程定义key:{}", pi.getProcessDefinitionKey());
    log.info("流程定义name:{}", pi.getProcessDefinitionName());
    log.info("流程定义version:{}", pi.getProcessDefinitionVersion());
  }


  //查询任务
  @Test
  public void queryAllTask() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //取得任务服务
    TaskService taskService = processEngine.getTaskService();
    //创建一个任务查询对象
    TaskQuery taskQuery = taskService.createTaskQuery();
    //办理人的任务列表
    List<Task> list = taskQuery.list();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //遍历任务列表
    if (list != null && !list.isEmpty()) {
      for (Task task : list) {
        log.info("任务的办理人：{}", task.getAssignee());
        log.info("任务的id：{}", task.getId());
        log.info("任务的名称：{}", task.getName());
        log.info("任务创建时间：{}", simpleDateFormat.format(task.getCreateTime()));
        log.info("----------------------------------------------------------");
      }
    }
  }


  //查询任务
  @Test
  public void queryTaskByAssignee() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    //任务的办理人：张三-》李四-》经理1，经理2，经理3
    String assignee = "经理2";
    //取得任务服务
    TaskService taskService = processEngine.getTaskService();
    //创建一个任务查询对象
    TaskQuery taskQuery = taskService.createTaskQuery();
    //办理人的任务列表
    List<Task> list = taskQuery.taskAssignee(assignee).list();
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    //遍历任务列表
    if (list != null && !list.isEmpty()) {
      for (Task task : list) {
        log.info("任务的办理人：{}", task.getAssignee());
        log.info("任务的id：{}", task.getId());
        log.info("任务的名称：{}", task.getName());
        log.info("任务创建时间：{}", simpleDateFormat.format(task.getCreateTime()));
        log.info("----------------------------------------------------------");
      }
    }
  }

  @Test
  public void completeApplyTask() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    processEngine.getTaskService().complete("10010");
    log.info("发起申请完成");
    queryAllTask();
  }

  @Test
  public void completeLeaderTask() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    HashMap<String, Object> variables = new HashMap<>();
    variables.put("leaderResult", "1");
    processEngine.getTaskService().complete("12502", variables);
    log.info("领导审批完成");
    queryAllTask();
  }

  @Test
  public void completeManagerTask() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    HashMap<String, Object> variables = new HashMap<>();
    variables.put("result", "1");
    processEngine.getTaskService().complete("7504", variables);
    log.info("经理审批完成");
  }

  /**
   * 获取下一个节点
   */
  @Test
  public void pvmTest() {
    ProcessEngine processEngine = ProcessEngines.getDefaultProcessEngine();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    RepositoryService rs = processEngine.getRepositoryService();
    TaskService taskService = processEngine.getTaskService();
    Task task = taskService.createTaskQuery().taskId("15004").singleResult();
    ProcessDefinition processDefinition = rs.getProcessDefinition(task.getProcessDefinitionId());

    ProcessDefinitionEntity def = (ProcessDefinitionEntity) ((RepositoryServiceImpl) rs).getDeployedProcessDefinition(task.getProcessDefinitionId());
    List<ActivityImpl> activitiList = def.getActivities();
    ExecutionEntity execution = (ExecutionEntity) runtimeService.createExecutionQuery().executionId(task.getExecutionId()).singleResult();
    String activitiId = execution.getActivityId();

    for (ActivityImpl activityImpl : activitiList) {
      if (activityImpl.getId().equals(activitiId)) {
        log.info("当前节点Id：{}", activityImpl.getId());
        log.info("当前节点名称：{}", activityImpl.getProperty("name"));
        List<PvmTransition> outTransitions = activityImpl.getOutgoingTransitions();
        for (PvmTransition pvmTransition : outTransitions) {
          PvmActivity destination = pvmTransition.getDestination();
          log.info("下个节点Id：{}", pvmTransition.getId());
          log.info("下个节点名称：{}", destination.getProperty("name"));
          log.info("下个节点备注：{}", destination.getProperty("name"));
        }
      }
    }
  }

}
