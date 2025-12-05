package vip.fairy;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.flowable.bpmn.model.BpmnModel;
import org.flowable.bpmn.model.FlowElement;
import org.flowable.bpmn.model.FlowNode;
import org.flowable.bpmn.model.Gateway;
import org.flowable.bpmn.model.SequenceFlow;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.history.HistoricProcessInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.image.ProcessDiagramGenerator;

@Slf4j
public class Test {

  static ProcessEngine processEngine;

  static {
    ProcessEngineConfiguration cfg = new StandaloneProcessEngineConfiguration().setJdbcUrl(
            "jdbc:mysql:///flowable7?nullCatalogMeansCurrent=true&serverTimezone=UTC&createDatabaseIfNotExist=true").setJdbcUsername("root")
        .setJdbcPassword("fairy-vip").setJdbcDriver("com.mysql.cj.jdbc.Driver")
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    processEngine = cfg.buildProcessEngine();

    ProcessEngineConfiguration processEngineConfiguration = processEngine.getProcessEngineConfiguration();
    processEngineConfiguration.setActivityFontName("宋体");
    processEngineConfiguration.setLabelFontName("宋体");
    processEngineConfiguration.setAnnotationFontName("宋体");
  }

  public static void main(String[] args) {
    System.out.println("应用启动，等待中...");
    //RuntimeService runtimeService = processEngine.getRuntimeService();
    //List<ProcessInstance> instances = runtimeService.createProcessInstanceQuery().list();
    //instances.forEach(instance -> System.out.println("Running instance: " + instance.getId()));

//    start();
    completeSubmit();
    waitForTimeoutReminder(3 * 60 * 1000); // 等待3分钟
  }

  /**
   * 等待超时提醒触发
   */
  private static void waitForTimeoutReminder(long millis) {
    try {
      log.info("等待超时提醒触发，等待时间: {}秒", millis / 1000);
      Thread.sleep(millis);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      log.warn("等待被中断", e);
    }
  }


  static void deployment() {
    RepositoryService repositoryService = processEngine.getRepositoryService();
    Deployment deployment = repositoryService.createDeployment().addClasspathResource("leaveApplicationProcess.bpmn20.xml").deploy();

    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();
    System.out.println("Found process definition : " + processDefinition.getName());
  }

  /**
   * 启动任务
   */
  static ProcessInstance start() {
    RuntimeService runtimeService = processEngine.getRuntimeService();

    Map<String, Object> variables = new HashMap<>();
    variables.put("leaveType", "annual"); // 请假类型，这里设为年假
    variables.put("startDate", "2024-01-01"); // 开始日期
    variables.put("endDate", "2024-01-05"); // 结束日期
    variables.put("reason", "回家探亲"); // 请假原因
    variables.put("attachment", "请假证明.pdf"); // 附件
    variables.put("employeeId", "123"); // 员工ID
    variables.put("employeeName", "张三"); // 员工姓名
    variables.put("supervisor", "李四"); // 直接上级
    variables.put("departmentManager", "王五"); // 部门经理
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("leaveApplicationProcess", variables);
    System.out.println("processInstanceId:" + processInstance.getId());
    return processInstance;
  }


  static void completeSubmit() {
    TaskService taskService = processEngine.getTaskService();
    taskService.complete("77520");
  }


  static void completeSubmit2() {
    Map<String, Object> variables = new HashMap<>();
    variables.put("supervisorApprovalResult", "approved"); // 请假类型，这里设为年假
    TaskService taskService = processEngine.getTaskService();
    taskService.complete("20005", variables);
  }


  static void currentProcess() {
    // 1. 创建流程引擎
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    HistoryService historyService = processEngine.getHistoryService();

    String processInstanceId = "17501";
    List<ProcessInstance> list = runtimeService.createProcessInstanceQuery().list();
    try {
      // 2. 获取流程实例
      ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

      if (processInstance == null) {
        // 如果流程实例已结束，从历史记录中获取
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
            .singleResult();
        if (historicProcessInstance == null) {
          System.out.println("未找到对应的流程实例");
          return;
        }
        processInstanceId = historicProcessInstance.getId();
      }

      // 获取流程定义ID
      String processDefinitionId = processInstance.getProcessDefinitionId();
      ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);

      // 获取当前活动节点ID列表
      List<String> activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);

      // 3. 生成执行图
      ProcessDiagramGenerator diagramGenerator = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
      InputStream inputStream = diagramGenerator.generateDiagram(repositoryService.getBpmnModel(processDefinitionId), "png", activeActivityIds,
          new ArrayList<>(), processEngine.getProcessEngineConfiguration().getActivityFontName(),
          processEngine.getProcessEngineConfiguration().getLabelFontName(), processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
          processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0, true);

      // 4. 保存执行图到本地文件
      try (FileOutputStream fileOutputStream = new FileOutputStream("process_diagram.png")) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
          fileOutputStream.write(buffer, 0, bytesRead);
        }
        System.out.println("执行图已保存到 process_diagram.png");
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }


  public static void generateProcessDiagramV1(String processInstanceId) {
    // 获取流程引擎
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    HistoryService historyService = processEngine.getHistoryService();

    try {
      // 获取流程实例
      ProcessInstance processInstance = runtimeService.createProcessInstanceQuery().processInstanceId(processInstanceId).singleResult();

      String processDefinitionId;
      List<String> activeActivityIds;

      if (processInstance != null) {
        // 流程仍在运行
        processDefinitionId = processInstance.getProcessDefinitionId();
        activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
      } else {
        // 流程已结束，从历史记录获取
        HistoricProcessInstance historicProcessInstance = historyService.createHistoricProcessInstanceQuery().processInstanceId(processInstanceId)
            .singleResult();

        if (historicProcessInstance == null) {
          System.out.println("未找到流程实例: " + processInstanceId);
          return;
        }

        processDefinitionId = historicProcessInstance.getProcessDefinitionId();

        // 获取所有已执行的活动
        List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
            .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();

        // 收集所有已执行的活动ID
        activeActivityIds = new ArrayList<>();
        for (HistoricActivityInstance activity : historicActivityInstances) {
          activeActivityIds.add(activity.getActivityId());
        }
      }

      // 获取流程定义
      ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);

      // 获取BPMN模型
      org.flowable.bpmn.model.BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

      // 获取已执行的流程线
      List<String> flowIds = getExecutedFlowsV1(processEngine, processInstanceId, bpmnModel);

      // 生成流程图
      ProcessDiagramGenerator diagramGenerator = processEngine.getProcessEngineConfiguration().getProcessDiagramGenerator();
      InputStream imageStream = diagramGenerator.generateDiagram(bpmnModel, "png", activeActivityIds, flowIds,
          processEngine.getProcessEngineConfiguration().getActivityFontName(),
          processEngine.getProcessEngineConfiguration().getLabelFontName(),
          processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
          processEngine.getProcessEngineConfiguration().getClassLoader(), 1.0,
          true);

      // 保存图片
      try (FileOutputStream fos = new FileOutputStream("process_diagram_" + processInstanceId + ".png")) {
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = imageStream.read(buffer)) != -1) {
          fos.write(buffer, 0, bytesRead);
        }
        System.out.println("流程图已生成: process_diagram_" + processInstanceId + ".png");
      }

    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static List<String> getExecutedFlowsV1(ProcessEngine processEngine, String processInstanceId, BpmnModel bpmnModel) {
    List<String> executedFlowIds = new ArrayList<>();
    HistoryService historyService = processEngine.getHistoryService();

    // 获取所有已执行的活动
    List<HistoricActivityInstance> historicActivityInstances = historyService.createHistoricActivityInstanceQuery()
        .processInstanceId(processInstanceId).orderByHistoricActivityInstanceStartTime().asc().list();

    // 遍历活动，找出连接它们的流程线
    for (int i = 0; i < historicActivityInstances.size() - 1; i++) {
      HistoricActivityInstance currentActivity = historicActivityInstances.get(i);
      HistoricActivityInstance nextActivity = historicActivityInstances.get(i + 1);

      // 获取当前活动所在的流程
      org.flowable.bpmn.model.Process process = bpmnModel.getProcesses().get(0);

      // 获取当前活动
      FlowElement currentElement = process.getFlowElement(currentActivity.getActivityId());

      if (currentElement instanceof Gateway) {
        // 如果是网关，需要找到连接到下一活动的出口流
        Gateway gateway = (Gateway) currentElement;
        for (SequenceFlow sequenceFlow : gateway.getOutgoingFlows()) {
          if (sequenceFlow.getTargetRef().equals(nextActivity.getActivityId())) {
            executedFlowIds.add(sequenceFlow.getId());
            break;
          }
        }
      } else if (currentElement instanceof FlowNode) {
        // 如果是普通流程节点
        FlowNode flowNode = (FlowNode) currentElement;
        for (SequenceFlow sequenceFlow : flowNode.getOutgoingFlows()) {
          if (sequenceFlow.getTargetRef().equals(nextActivity.getActivityId())) {
            executedFlowIds.add(sequenceFlow.getId());
            break;
          }
        }
      }
    }

    return executedFlowIds;
  }


  public static void generateProcessDiagramV2(String processInstanceId) {
    RepositoryService repositoryService = processEngine.getRepositoryService();
    RuntimeService runtimeService = processEngine.getRuntimeService();
    HistoryService historyService = processEngine.getHistoryService();

    // 获取流程实例（区分运行中/已结束）
    ProcessInstance processInstance = runtimeService.createProcessInstanceQuery()
        .processInstanceId(processInstanceId)
        .singleResult();
    HistoricProcessInstance historicProcessInstance = null;
    boolean isProcessEnded = false;
    if (processInstance == null) {
      historicProcessInstance = historyService.createHistoricProcessInstanceQuery()
          .processInstanceId(processInstanceId)
          .singleResult();
      isProcessEnded = true;
      if (historicProcessInstance == null) {
        System.out.println("流程实例不存在: " + processInstanceId);
        return;
      }
    }

    // 获取流程定义、BPMN 模型
    String processDefinitionId = isProcessEnded ?
        historicProcessInstance.getProcessDefinitionId() : processInstance.getProcessDefinitionId();
    ProcessDefinition processDefinition = repositoryService.getProcessDefinition(processDefinitionId);
    BpmnModel bpmnModel = repositoryService.getBpmnModel(processDefinitionId);

    // 收集已执行的活动节点 ID
    List<String> activeActivityIds = new ArrayList<>();
    if (!isProcessEnded) {
      activeActivityIds = runtimeService.getActiveActivityIds(processInstanceId);
    } else {
      List<HistoricActivityInstance> historicActivities = historyService.createHistoricActivityInstanceQuery()
          .processInstanceId(processInstanceId)
          .orderByHistoricActivityInstanceStartTime().asc()
          .list();
      for (HistoricActivityInstance activity : historicActivities) {
        activeActivityIds.add(activity.getActivityId());
      }
    }

    // 收集已执行的流转线 ID
    List<String> executedFlowIds = getExecutedFlowIdsV2(processEngine, processInstanceId, bpmnModel, isProcessEnded);

    // 生成带高亮的流程图
    ProcessDiagramGenerator diagramGenerator = processEngine.getProcessEngineConfiguration()
        .getProcessDiagramGenerator();
    InputStream diagramStream = diagramGenerator.generateDiagram(
        bpmnModel,
        "png",
        activeActivityIds,
        executedFlowIds,
        processEngine.getProcessEngineConfiguration().getActivityFontName(),
        processEngine.getProcessEngineConfiguration().getLabelFontName(),
        processEngine.getProcessEngineConfiguration().getAnnotationFontName(),
        processEngine.getProcessEngineConfiguration().getClassLoader(),
        1.0,
        true  // 是否高亮，true 则已执行元素会特殊标记
    );

    // 保存流程图到本地（可根据需求调整输出方式，如返回给前端）
    try (FileOutputStream fos = new FileOutputStream("process-diagram-" + processInstanceId + ".png")) {
      byte[] buffer = new byte[1024];
      int len;
      while ((len = diagramStream.read(buffer)) != -1) {
        fos.write(buffer, 0, len);
      }
      System.out.println("流程图已生成：process-diagram-" + processInstanceId + ".png");
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  /**
   * 筛选已执行的流转线 ID
   */
  private static List<String> getExecutedFlowIdsV2(ProcessEngine processEngine, String processInstanceId,
      BpmnModel bpmnModel, boolean isProcessEnded) {
    List<String> executedFlowIds = new ArrayList<>();
    HistoryService historyService = processEngine.getHistoryService();
    List<HistoricActivityInstance> historicActivities = historyService.createHistoricActivityInstanceQuery()
        .processInstanceId(processInstanceId)
        .orderByHistoricActivityInstanceStartTime().asc()
        .list();

    // 遍历历史活动，找前后节点关联的流转线
    for (int i = 0; i < historicActivities.size() - 1; i++) {
      HistoricActivityInstance currentActivity = historicActivities.get(i);
      HistoricActivityInstance nextActivity = historicActivities.get(i + 1);

      FlowElement currentFlowElement = bpmnModel.getMainProcess()
          .getFlowElement(currentActivity.getActivityId());
      if (currentFlowElement instanceof FlowNode) {
        FlowNode currentNode = (FlowNode) currentFlowElement;
        // 遍历当前节点的出流线，匹配下一个节点
        for (SequenceFlow outgoingFlow : currentNode.getOutgoingFlows()) {
          if (outgoingFlow.getTargetRef().equals(nextActivity.getActivityId())) {
            executedFlowIds.add(outgoingFlow.getId());
            break;
          }
        }
      } else if (currentFlowElement instanceof Gateway) {
        // 网关特殊处理（排他、并行等，逻辑类似，根据实际网关类型可细化）
        Gateway gateway = (Gateway) currentFlowElement;
        for (SequenceFlow outgoingFlow : gateway.getOutgoingFlows()) {
          if (outgoingFlow.getTargetRef().equals(nextActivity.getActivityId())) {
            executedFlowIds.add(outgoingFlow.getId());
            break;
          }
        }
      }
    }
    return executedFlowIds;
  }
}
