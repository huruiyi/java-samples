package org.flowable.holidayrequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.flowable.engine.HistoryService;
import org.flowable.engine.ProcessEngine;
import org.flowable.engine.ProcessEngineConfiguration;
import org.flowable.engine.RepositoryService;
import org.flowable.engine.RuntimeService;
import org.flowable.engine.TaskService;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.impl.cfg.StandaloneProcessEngineConfiguration;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;

public class App {

  static final ProcessEngineConfiguration cfg;
  static final ProcessEngine processEngine;
  static final RepositoryService repositoryService;
  static final RuntimeService runtimeService;

  static {
//    cfg = new StandaloneProcessEngineConfiguration()
//        .setJdbcUrl("jdbc:mysql://127.0.0.1:3306/flowable?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC")
//        .setJdbcUsername("root")
//        .setJdbcPassword("root")
//        .setJdbcDriver("com.mysql.cj.jdbc.Driver")
//        .setDatabaseSchemaUpdate(AbstractEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);

    cfg = new StandaloneProcessEngineConfiguration()
        .setJdbcUrl("jdbc:h2:mem:flowable;DB_CLOSE_DELAY=-1")
        .setJdbcUsername("sa")
        .setJdbcPassword("")
        .setJdbcDriver("org.h2.Driver")
        .setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    processEngine = cfg.buildProcessEngine();
    repositoryService = processEngine.getRepositoryService();
    runtimeService = processEngine.getRuntimeService();
  }


  public static void main(String[] args) {
    start();
  }

  public static void start() {
    Deployment deployment = repositoryService
        .createDeployment()
        .addClasspathResource("holiday-request.bpmn20.xml")
        .deploy();

    ProcessDefinition processDefinition = repositoryService.createProcessDefinitionQuery().deploymentId(deployment.getId()).singleResult();

    System.out.println("Found process definition : " + processDefinition.getName());

    Map<String, Object> variables = new HashMap<>();

    Scanner scanner = new Scanner(System.in);

    System.out.println("Who are you?");
    String employee = scanner.nextLine();

    System.out.println("How many holidays do you want to request?");
    Integer nrOfHolidays = Integer.valueOf(scanner.nextLine());

    System.out.println("Why do you need them?");
    String description = scanner.nextLine();
    variables.put("employee", employee);
    variables.put("nrOfHolidays", nrOfHolidays);
    variables.put("description", description);

    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest", variables);

    TaskService taskService = processEngine.getTaskService();
    List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("managers").list();
    System.out.println("You have " + tasks.size() + " tasks:");
    for (int i = 0; i < tasks.size(); i++) {
      String taskId = tasks.get(i).getId();
      Map<String, Object> taskInfo = taskService.getVariables(taskId);

      System.out.println((i + 1) + ") " + tasks.get(i).getName() + "[ employee :" + taskInfo.get("employee") + "]");
    }

    System.out.println("Which task would you like to complete?");
    int taskIndex = Integer.parseInt(scanner.nextLine());
    Task task = tasks.get(taskIndex - 1);
    Map<String, Object> processVariables = taskService.getVariables(task.getId());
    System.out.println(processVariables.get("employee") + " wants " + processVariables.get("nrOfHolidays") + " of holidays. Do you approve this?");

    boolean approved = scanner.nextLine().equalsIgnoreCase("y");
    variables = new HashMap<>();
    variables.put("approved", approved);
    taskService.complete(task.getId(), variables);
  }

  public static void historyServicePrint() {
    //ACT_RE_PROCDEF
    ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holidayRequest");
    HistoryService historyService = processEngine.getHistoryService();
    List<HistoricActivityInstance> activities =
        historyService.createHistoricActivityInstanceQuery()
            .processInstanceId(processInstance.getId())
            .finished()
            .orderByHistoricActivityInstanceEndTime().asc()
            .list();
    System.out.println("********************************************************************************");
    for (HistoricActivityInstance activity : activities) {
      System.out.println(activity.getActivityId() + " took " + activity.getDurationInMillis() + " milliseconds");
    }
    System.out.println("********************************************************************************");
  }
}
