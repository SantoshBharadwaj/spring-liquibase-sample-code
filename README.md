This repository contains code that explains how liquibase works along with Spring Boot.  
(Not aware of liquibase? Check out https://www.liquibase.org/ )

You can import this as a Maven project and try it out yourself!

Once you have imported this project, you can either use any IDE to build it or if you have maven installed in your system, just run below command on the parent folder (Folder that contains pom.xml file)  
_mvn clean install_

Once you see the build is successful and the jar file is generated, you can now run this as a Spring Boot application directly from your IDE or run the below command  
_mvn spring-boot:run_

Now observe the application logs.  
You would be able to see that a H2 In-Memory database has been created. You can also verify this by connecting to below URL from your browser.  
_http://localhost:8080/h2-console_

This application runs the liquibase scripts on startup itself. It is upto you where you want the scripts to be executed.  
For example: You can expose an API and on call of it, you could start executing liquibase operations.  

Once Liquibase has been triggered, it starts executing the DB scripts one by one provided to them as a change log XML file.  
This XML file contains 1 or more files where each file could contain 1 or more changesets.  
Liquibase maintains each changeset in its internal tables thereby providing a version control feature for database.  

Note that, this is an in-memory database. So, database gets deleted once you start and stop the application.  


