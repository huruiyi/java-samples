package vip.fairy;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.GenericApplicationContext;
import vip.fairy.config.BatchConfig;

import java.util.Date;

public class SingerJobDemo {

  /**
   *  org.springframework.batch.core.schema-mysql.sql
   */
  public static void main(String... args) throws Exception {
    GenericApplicationContext ctx = new AnnotationConfigApplicationContext(BatchConfig.class);

    Job job = ctx.getBean(Job.class);
    JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
    JobParameters jobParameters = new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
    jobLauncher.run(job, jobParameters);

    System.in.read();
    ctx.close();
  }
}
