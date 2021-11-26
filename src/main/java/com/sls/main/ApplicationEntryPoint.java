package com.sls.main;

import javax.sql.DataSource;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import com.sls.impl.Constants;

@SpringBootApplication
@ComponentScan(basePackages = {"com.sls.impl"})
public class ApplicationEntryPoint {

  public static void main(String[] args) {
    SpringApplication.run(ApplicationEntryPoint.class, args);
  }

  /**
   * This bean creates a DataSource which we pass it on to the Liquibase for creating the DB
   * connection. This DataSource will contain all the required values like URL, Schema, Username and
   * Password which the liquibase will use to execute SQL scripts
   * 
   * @return DataSource
   */
  @Bean("H2DataSource")
  public DataSource dataSource() {
    DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
    driverManagerDataSource.setDriverClassName("org.h2.Driver");
    driverManagerDataSource
        .setUrl("jdbc:h2:mem:spring-liquibase-db;DB_CLOSE_DELAY=-1;INIT=CREATE SCHEMA IF NOT EXISTS \"" + Constants.SCHEMA_NAME + "\"");
    driverManagerDataSource.setUsername(Constants.DB_USERNAME);
    driverManagerDataSource.setPassword(Constants.DB_PASSWORD);
    driverManagerDataSource.setSchema(Constants.SCHEMA_NAME);
    return driverManagerDataSource;
  }

}
