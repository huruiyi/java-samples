package vip.fairy.service;

import java.time.format.DateTimeFormatter;
import java.util.concurrent.ThreadLocalRandom;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.flowable.engine.delegate.DelegateExecution;
import org.flowable.engine.delegate.JavaDelegate;
import org.flowable.engine.impl.el.JuelExpression;


@Data
@Slf4j
public class CalculateLeaveDaysService implements JavaDelegate {

  private JuelExpression startDate;
  private JuelExpression endDate;

  private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

  @Override
  public void execute(DelegateExecution execution) {
    // 获取流程变量中的开始日期和结束日期
    String startDateStr = (String) execution.getVariable("startDate");
    String endDateStr = (String) execution.getVariable("endDate");
    log.info("计算请假天数: 开始日期={}, 结束日期={}", startDateStr, endDateStr);

    // 计算请假天数
    int leaveDays = ThreadLocalRandom.current().nextInt(2);

    // 设置计算结果到流程变量
    execution.setVariable("leaveDays", leaveDays);

    log.info("请假天数计算完成: {}天", leaveDays);
  }


}
