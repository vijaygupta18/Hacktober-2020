# URL Compactor Web App

![Demo image of main screen](help/img/compactor_demo_page.png?raw=true "Title")

## What is this?
Spring powered Web App for compacting (or should we say shortening) URLs provided by user via submit form.

## Pre-requisites
1. Maven of version 3.x. mvn tools should be visible via target user's Path
2. JDK 1.8. java, javac tools should visible via target user's Path
3. Pre-configured database. Either in-memory, file or Production-ready
4. Internet access for Maven dependencies
5. Free disk space for .m2 dependencies file cache. Folder should have write access for the target user, of course

## Components
1. Source code: located under /src directory
2. Build file: pom.xml Maven descriptor
3. config files. They are: built-in (embeddable resource) application.properties file for debug/dev purposes.
Sample prod.properties file as an example of prod configuration
4. Package of unit tests located in src/test/java/ directory

## Build Guide
1. Dev/Debug mode
Execute the following command in the console
```
mvn -Pdebug clean install
```
or just build the project in IDE with *debug* as an active Maven profile assuming that the project has been imported as a *Maven project*
This will result in *.jar executable file located in *$PROJECT* folder
2.
Execute the following command in the console
   ```
   mvn clean install
   ```
   or just build the project in IDE with disabled *debug* Maven profile assuming that the project has been imported as a *Maven project*
## Run & Configuration Guide
Being in the *$PROJECT* folder execute the following command in the console (check the correct target file name after build)
```
java -jar url-compactor-service-0.0.1-SNAPSHOT.jar
```
or Run main class *UrlCompactorServiceApplication.java* via IDE once the project has been imported as Maven Project

To use custom configuration properties use system property *Dspring.config.location*. Below is the example:
```
java -jar -Dspring.config.location=file:/home/targetUser/prod.properties url-compactor-service-0.0.1-SNAPSHOT.jar
```
One can accommodate provided prod.properties from the main/resources dir. Configure it for your needs and DB connectivity settings.
Below is the short description of each service specific property:
```
#Context path for REST API of the service
rest.prefix=rest
#Number of retries to re-create pseudo hash for URL in case there was a collision/data integrity violation
creation.retry.count=3
#Limit of how much most visited URLs service can provide
max.top.visited.count=10

#Web App http port. 80 is the default for browser applications
server.port: 9090
#Keep it actual with the host there the service is executed. Check that your DNS record navigates to the right machine
base.url=http://localhost
#Spring debug/profiling specific setting
management.port: 9001
#Spring debug/profiling specific setting
management.address: 127.0.0.1
```

## TODO:
1. Documentation (+)
2. Maven profiles: dev, prod, qa, demo/debug etc. (+-)
3. Different DataSource configurations and application.properties (+)
4. Provide -Dproperties support for dynamic flexible configuration (+) * documented how-to
5. Resolve duplicated origin URLs problem (give already shortened link, why not?) (-)
6. More test, including integration ones (-)
7. NEW Feature (?): Custom User Short links. Let the user generate short link using his own "pattern" (-)
8. CI Pipeline (?): Configure remote Node to check each pushed tag: detect push, build and run tests, generate reports, provide artifacts, notify all involved parties, provide metrics, history etc. Jenkins node? (-)