package vip.fairy.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

/**
 * Created by iuliana.cosmina on 7/30/17.
 */
@Configuration
public class DataSourceConfig {

    private static Logger logger = LoggerFactory.getLogger(DataSourceConfig.class);

    @Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/test_batch");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }

//    @Bean
//    public DataSource dataSource() {
//        try {
//            EmbeddedDatabaseBuilder dbBuilder = new EmbeddedDatabaseBuilder();
//            return dbBuilder
//                    .setType(EmbeddedDatabaseType.H2)
//                    .addScripts("classpath:/org/springframework/batch/core/schema-h2.sql", "classpath:support/singer.sql").build();
//        } catch (Exception e) {
//            logger.error("Embedded DataSource bean cannot be created!", e);
//            return null;
//        }
//    }
}
