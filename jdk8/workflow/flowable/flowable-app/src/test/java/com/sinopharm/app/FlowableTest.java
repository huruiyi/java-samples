package com.sinopharm.app;

import jakarta.annotation.Resource;
import org.flowable.engine.*;
import org.flowable.engine.history.HistoricActivityInstance;
import org.flowable.engine.repository.Deployment;
import org.flowable.engine.repository.ProcessDefinition;
import org.flowable.engine.runtime.ProcessInstance;
import org.flowable.task.api.Task;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest(classes = AppStart.class)
public class FlowableTest {

    @Resource
    private ProcessEngine processEngine;

    @Test
    public void createProcessEngine(){
        System.out.println(processEngine.toString());
    }

    //部署一个流程
    @Test
    public void deployProcess(){
        RepositoryService repositoryService = processEngine.getRepositoryService();
        Deployment deployment = repositoryService.createDeployment()
                .addClasspathResource("processes/holiday-request.bpmn20.xml")
                .deploy();
        System.out.println("流程部署ID：" + deployment.getId());
    }

    //查询部署的流程
    @Test
    public void queryDeploy() {
        //act_re_procdef.deployment_id_
        ProcessDefinition processDefinition = processEngine.getRepositoryService()
            .createProcessDefinitionQuery()
            .deploymentId("6373952a-7505-11ef-af35-005056c00001")
            .singleResult();
        System.out.println("流程定义信息-Id：" + processDefinition.getId());
        System.out.println("流程定义信息-Name：" + processDefinition.getName());
    }

    //启动一个流程实例
    @Test
    public void startProcessInstance(){
        RuntimeService runtimeService = processEngine.getRuntimeService();
        //定义流程变量
        Map<String,Object> variables = new HashMap<>();
        variables.put("day",10);
        ProcessInstance processInstance = runtimeService.startProcessInstanceByKey("holiday-request",variables); //这个key是xml文件里面process元素的id属性
        //runtimeService.startProcessInstanceById() //根据ID来启动，这个ID是数据库表里面的ID
        //8c247508-1bfd-11ef-b157-1e4d08627f39
        System.out.println("流程实例："+processInstance.getId());
    }

    //获取某个候选组的任务列表
    @Test
    public void getTaskListByGroup(){
        TaskService taskService = processEngine.getTaskService();
        //根据候选组来查询任务
        List<Task> tasks = taskService.createTaskQuery().taskCandidateGroup("ceo").list();
        System.out.println("你有" + tasks.size() + "个任务");
        for(Task task : tasks){
            System.out.println("任务名称："+task.getName()+",任务ID："+task.getId());
        }
    }

    //获取某个用户的任务列表
    @Test
    public void getTaskListByUser(){
        TaskService taskService = processEngine.getTaskService();
        //根据候选组来查询任务
        List<Task> tasks = taskService.createTaskQuery().taskAssignee("lisi").list();
        System.out.println("你有" + tasks.size() + "个任务");
        for(Task task : tasks){
            System.out.println("任务名称："+task.getName()+",任务ID："+task.getId());
        }
    }

    //获取任务的变量信息
    @Test
    public void getTaskVariables(){
        TaskService taskService = processEngine.getTaskService();
        List<Task> list =taskService.createTaskQuery().list();

        //获取某一个任务
        Task task = taskService.createTaskQuery().taskId(list.get(0).getId()).singleResult();
        //获取任务的变量信息
        Map<String,Object> variables = taskService.getVariables(task.getId());
        System.out.println("变量信息："+variables.toString());
    }

    //完成一个任务
    @Test
    public void taskFinish(){
        Map<String,Object> variables = new HashMap<>();
        variables.put("approved",true);

        String taskId = "30e2c3af-6b5e-11ef-baeb-9c7befa998dd";
        TaskService taskService = processEngine.getTaskService();
        taskService.complete(taskId,variables);
    }

    //查询历史记录
    @Test
    public void getHistory(){
        HistoryService historyService = processEngine.getHistoryService();
        List<HistoricActivityInstance> activities = historyService.createHistoricActivityInstanceQuery()
                .processInstanceId("c1a64d55-1bfa-11ef-b8b2-1e4d08627f39")
                .finished() //要已完成的
                .orderByHistoricActivityInstanceEndTime().asc().list();

        for(HistoricActivityInstance activity : activities){
            System.out.println("活动名称："+activity.getActivityName()+" 花费了" + activity.getDurationInMillis() + "毫秒");
        }
    }


}
