```shell
java -jar target/spring-boot-logging-0.0.1-SNAPSHOT.jar --trace
```

```shell
-Dlogging.level.org.springframework=TRACE 
-Dlogging.level.vip.fairy=TRACE
```

```shell
mvn spring-boot:run 
  -Dspring-boot.run.arguments=--logging.level.org.springframework=TRACE,--logging.level.vip.fairy=TRACE
```

```shell
./gradlew bootRun -Pargs=--logging.level.org.springframework=TRACE,--logging.level.vip.fairy=TRACE
```

```shell
logging.level.root=WARN
logging.level.vip.fairy=TRACE
```

```shell
<logger name="org.springframework" level="INFO" />
<logger name="vip.fairy" level="INFO" />
```

spring boot默认的日志格式
```shell
%d{yyyy-MM-dd HH:mm:ss.SSS} %5p ${PID:-} [%15.15t] %-40.40logger{39} : %m%n
```

logback默认的日志格式
```shell
%d{HH:mm:ss.SSS} [%thread] %-5level %logger{36} - %msg%n
```

```shell
%d{MM-dd HH:mm:ss.SSS} [%5level] %4line %40.40logger{39}.%-30.30method : %m%n
```
```shell
 
%d{yyyy-MM-dd HH:mm:ss.SSS} %highlight{%6p} %style{%5pid}{bright,magenta} --- [%15.15t] %style{%-40.40logger{39}}{bright,cyan}: %m%n
按照springBoot 默认格式 + 添加颜色
```
参考：https://howtodoinjava.com/log4j2/useful-conversion-pattern-examples/
### Simple Log Formatting
```shell
%d [%p] %c{1} - %m%n
```
### Left Justified Log Level
```shell
%d [%-6p] %c{1} - %m%n
```
### Printing Package Information
```shell
%d [%-6p] %c{1} - %m%n
Use %c{1} for printing the complete package level. It will generate the below output:

%d [%-6p] %c{3} - %m%n
%c{3} will print the package level upto two levels.
```
### Custom Date Pattern
```shell
%d{yyyy/MM/dd HH:mm:ss,SSS} [%-6p] %c{1} - %m%n
```
### Detailed File Name, Method Name and Line Number
```shell
%d [%-6p] %C{1}.%M(%F:%L) - %m%n
```

### Fully Detailed Information
```shell
%sn %d{yyyy/MM/dd HH:mm:ss,SSS} %r [%-6p] [%t] %c{3} %C{3}.%M(%F:%L) - %m%n
```

### Patterns to Display Hours 
‘hh’ – hours in 12 hour format
‘HH’ – hours in 24 hour format
‘a’ – display the AM/PM information.

hh:mm:ss a



-Dlog4j2.configurationFile=file:/home/lokesh/log4j2.xml


