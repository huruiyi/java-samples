package vip.fairy.config;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.repository.support.JobRepositoryFactoryBean;
import org.springframework.batch.integration.launch.JobLaunchingGateway;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.task.SyncTaskExecutor;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Transformer;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.file.dsl.Files;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.MessageChannel;
import vip.fairy.MessageToJobLauncher;
import vip.fairy.Singer;
import vip.fairy.SingerItemProcessor;
import vip.fairy.StepExecutionStatsListener;

import javax.sql.DataSource;
import java.io.File;

@Configuration
@EnableIntegration
@IntegrationComponentScan
@EnableBatchProcessing
@Import(DataSourceConfig.class)
@ComponentScan("vip.fairy")
public class BatchConfig {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    DataSource dataSource;

    @Autowired
    StepExecutionStatsListener executionStatsListener;

    @Autowired
    SingerItemProcessor itemProcessor;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    Job job;

    @Bean
    @Transformer(inputChannel = "inbound", outputChannel = "outbound")
    public MessageToJobLauncher messageToJobLauncher() {
        return new MessageToJobLauncher(singerJob(), "file.name");
    }

    @Bean
    public MessageChannel inbound() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel outbound() {
        return new DirectChannel();
    }

    @Bean
    public MessageChannel loggingChannel() {
        return new DirectChannel();
    }

    @Bean
    public Job singerJob() {
        return jobs.get("singerJob").start(step1()).build();
    }

    @Bean
    protected Step step1() {
        return steps.get("step1").listener(executionStatsListener)
                .<Singer, Singer>chunk(10)
                .reader(itemReader(null))
                .processor(itemProcessor)
                .writer(itemWriter())
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemReader itemReader(@Value("file:#{jobParameters['file.name']}") String filePath) {
        FlatFileItemReader itemReader = new FlatFileItemReader();
        itemReader.setResource(resourceLoader.getResource(filePath));
        DefaultLineMapper lineMapper = new DefaultLineMapper();
        DelimitedLineTokenizer tokenizer = new DelimitedLineTokenizer();
        tokenizer.setNames("firstName", "lastName", "song");
        tokenizer.setDelimiter(",");
        lineMapper.setLineTokenizer(tokenizer);
        BeanWrapperFieldSetMapper<Singer> fieldSetMapper = new BeanWrapperFieldSetMapper<>();
        fieldSetMapper.setTargetType(Singer.class);
        lineMapper.setFieldSetMapper(fieldSetMapper);
        itemReader.setLineMapper(lineMapper);
        return itemReader;
    }

    @Bean
    public ItemWriter<Singer> itemWriter() {
        JdbcBatchItemWriter<Singer> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        itemWriter.setSql("INSERT INTO singer (first_name, last_name, song) VALUES (:firstName, :lastName, :song)");
        itemWriter.setDataSource(dataSource);
        return itemWriter;
    }



}
