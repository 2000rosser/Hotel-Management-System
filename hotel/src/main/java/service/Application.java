package service;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;
import javax.sql.DataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    // @Bean
    // public DataSource getDataSource() {
    //     DataSourceBuilder dataSourceBuilder = DataSourceBuilder.create();
    //     dataSourceBuilder.driverClassName("org.h2.Driver");
    //     dataSourceBuilder.url("jdbc:h2:mem:hotel;DB_CLOSE_DELAY=-1;DB_CLOSE_ON_EXIT=FALSE");
    //     dataSourceBuilder.username("sa");
    //     dataSourceBuilder.password("password");
    //     return dataSourceBuilder.build();
    // }

    // @Bean
    // public JdbcTemplate jdbcTemplate(DataSource dataSource) {
    //     return new JdbcTemplate(dataSource);
    // }
}
