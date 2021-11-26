package com.sls.impl;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.DependsOn;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import liquibase.exception.LiquibaseException;
import liquibase.integration.spring.SpringLiquibase;

@Component
@DependsOn("H2DataSource")
public class LiquibaseExecutor {

  @Autowired
  @Qualifier("H2DataSource")
  private DataSource dataSource;

  @Autowired
  private ResourceLoader resourceLoader;

  /**
   * Post instantiation of this class, liquibase pulls up the database changelog file that contains
   * many XML files. Each XML file can be treated as a single source for all SQL scripts that need
   * to be executed for a particular production release.
   * 
   */
  @PostConstruct
  public void executeScripts() {
    try {
      SpringLiquibase liquibase = new SpringLiquibase();
      liquibase.setChangeLog("classpath:changelog/db-changelogs.xml");
      liquibase.setDataSource(dataSource);
      liquibase.setDefaultSchema(Constants.SCHEMA_NAME);
      liquibase.setResourceLoader(resourceLoader);
      liquibase.setShouldRun(true);
      liquibase.afterPropertiesSet();
    } catch (LiquibaseException e) {
      // Failed to execute liquibase scripts!
    }
  }

}
