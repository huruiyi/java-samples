package org.example;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.ProcessEngines;
import org.junit.jupiter.api.Test;

@Slf4j
public class TestProcessEngine {

  @Test
  public void createdInMemProcessEngine() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneInMemProcessEngineConfiguration();
    log.info("ProcessEngineConfiguration:{}", configuration);
    ProcessEngine processEngine = configuration.buildProcessEngine();
    log.info("ProcessEngine:{}", processEngine);
  }


  @Test
  public void createProcessEngineByBuildProcessEngineTest() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResourceDefault();
    log.info("ProcessEngineConfiguration:{}", configuration);

    ProcessEngine processEngine = configuration.buildProcessEngine();
    log.info("ProcessEngine:{}", processEngine);
  }

  @Test
  public void testCreateFromInputStream() throws Exception {
    File file = new File("src/main/resources/activiti.cfg2.xml");
    // 获取文件输入流
    InputStream inputStream = Files.newInputStream(file.toPath());
    ProcessEngineConfiguration configuration =
        ProcessEngineConfiguration.createProcessEngineConfigurationFromInputStream(inputStream, "processEngineConfiguration");
    log.info("ProcessEngineConfiguration:{}", configuration);
  }

  @Test
  public void testCreateFromResource() {
    ProcessEngineConfiguration configuration =
        ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg2.xml", "processEngineConfiguration");
    log.info("ProcessEngineConfiguration:{}", configuration);
  }

  @Test
  void testCreateFromStandalone() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createStandaloneProcessEngineConfiguration();
    configuration.setJdbcDriver("com.mysql.cj.jdbc.Driver");
    configuration.setJdbcUrl("jdbc:mysql://localhost:3306/activiti6?createDatabaseIfNotExist=true&useUnicode=true&characterEncoding=utf8&nullCatalogMeansCurrent=true");
    configuration.setJdbcUsername("root");
    configuration.setJdbcPassword("fairy-vip");
    //  设置创建表的策略 （当没有表时，自动创建表）
    //  DB_SCHEMA_UPDATE_FALSE = "false";//不会自动创建表，没有表，则抛异常
    //  DB_SCHEMA_UPDATE_CREATE_DROP = "create-drop";//先删除，再创建表
    //  DB_SCHEMA_UPDATE_TRUE = "true";//假如没有表，则自动创建
    configuration.setDatabaseSchemaUpdate(ProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    log.info("ProcessEngineConfiguration:{}", configuration);

    //通过ProcessEngineConfiguration对象创建 ProcessEngine 对象
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }


  @Test
  public void createProcessEngineByInitTest() {
    //读取activiti.cfg.xml配置文件，创建工作流引擎对象缓存到Map中
    ProcessEngines.init();
    //获取Map
    Map<String, ProcessEngine> enginesMap = ProcessEngines.getProcessEngines();
    //获取key为default的对象
    ProcessEngine processEngine = enginesMap.get("default");
    System.out.println(processEngine);
  }

  @Test
  public void defaultProcessEngine() {
    ProcessEngine defaultProcessEngine = ProcessEngines.getDefaultProcessEngine();
    System.out.println(defaultProcessEngine);
  }

  @Test
  public void defaultProcessEngineFromResource() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }

  @Test
  void test0() {
    ProcessEngineConfiguration configuration = ProcessEngineConfiguration.createProcessEngineConfigurationFromResource("activiti.cfg.xml");
    ProcessEngine processEngine = configuration.buildProcessEngine();
    System.out.println(processEngine);
  }


}
