package com.example.batch;

import java.util.Date;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SingerJobDemo {

  public static void main(String... args) throws Exception {

    ApplicationContext applicationContext = new ClassPathXmlApplicationContext("/spring/singerJob.xml");

    Job job = applicationContext.getBean(Job.class);

    JobParameters jobParameters1 = new JobParametersBuilder().addDate("date", new Date(2552423432L)).toJobParameters();
    JobParameters jobParameters2 = new JobParametersBuilder().addDate("date", new Date(2342423432L)).toJobParameters();
    JobParameters jobParameters3 = new JobParametersBuilder().addDate("date", new Date(2348723432L)).toJobParameters();

    JobLauncher jobLauncher = applicationContext.getBean(JobLauncher.class);
    jobLauncher.run(job, jobParameters1);
    jobLauncher.run(job, jobParameters2);
    jobLauncher.run(job, jobParameters3);

  }
}
