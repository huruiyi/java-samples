java.util.logging的Log Leve,The levels in descending order are:
##### `OFF, SEVERE, WARNING, INFO, CONFIG, FINE, FINER, FINEST, ALL`
- SEVERE (highest value)
- WARNING
- INFO
- CONFIG
- FINE
- FINER
- FINEST (lowest value)

https://www.slf4j.org/legacy.html
![legacy.png](images%2Flegacy.png)
https://www.slf4j.org/manual.html
![binding.png](images%2Fbinding.png)


- [关于log4j、jul、jcl、slf4j等等日志组件的理解](https://www.cnblogs.com/love-yk/p/12785780.html)
- [slf4j、log4j、logback的关系_lockback 和 slj4j 是什么关系](https://blog.csdn.net/u012894692/article/details/80308826)
- [logback的使用和logback.xml详解](https://www.cnblogs.com/warking/p/5710303.html)
- [java日志框架JUL、JCL、Slf4j、Log4j、Log4j2、Logback 一网打尽](https://blog.csdn.net/qq_27184497/article/details/122010178)
- [图文并茂讲解Java日志体系 ——slf4j和log4j、log4j2、logback](https://www.cnblogs.com/littlewhiterabbit/p/14171484.html)
- [JUL、JCL、Log4j、Slf4j各种日志框架的使用](https://blog.csdn.net/u022812849/article/details/115352057)
- [快速了解常用日志技术(JCL、Slf4j、JUL、Log4j、Logback、Log4j2)-阿里云开发者社区](https://developer.aliyun.com/article/1135512)

- [Java 日志体系](https://www.cnblogs.com/binarylei/p/9828166.html)
- [Java 日志体系（二）jcl 和 slf4j](https://www.cnblogs.com/binarylei/p/10781582.html)

- [SLF4J 和 Commons-Logging 日志工具的区别](http://ifeve.com/simplifying-distinction-between-sl4j/)
- [jcl 与 jul、log4j1、log4j2、logback 集成](https://jybzjf.iteye.com/blog/2238792)
- [Commons-Logging 存在的 ClassLoader 问题详解](https://yq.aliyun.com/articles/46888)

log4j日志格式：
```
%m   输出代码中指定的消息  
%p   输出优先级，即DEBUG，INFO，WARN，ERROR，FATAL   
%r   输出自应用启动到输出该log信息耗费的毫秒数   
%c   输出所属的类目，通常就是所在类的全名   
%t   输出产生该日志事件的线程名   
%n   输出一个回车换行符，Windows平台为“\r\n”，Unix平台为“\n”   
%d   输出日志时间点的日期或时间，默认格式为ISO8601，也可以在其后指定格式，比如：%d{yyy MMM dd HH:mm:ss , SSS}，输出类似：2002年10月18日 22 ： 10 ： 28 ， 921   
%l   输出日志事件的发生位置，包括类目名、发生的线程，以及在代码中的行数。举例：Testlog4.main(TestLog4.java: 10 )   
%x   输出和当前线程相关联的NDC(嵌套诊断环境),尤其用到像java servlets这样的多客户多线程的应用中。
-X   输出信息时左对齐；
%%   输出一个"%"字符
%F   输出日志消息产生时所在的文件名称
%L   输出代码中的行号
%n   输出一个回车换行符，Windows平台为"\r\n"，Unix平台为"\n"输出日志信息换行

可以在%与模式字符之间加上修饰符来控制其最小宽度、最大宽度、和文本的对齐方式。如：

1)%20c：指定输出category的名称，最小的宽度是20，如果category的名称小于20的话，默认的情况下右对齐。
2)%-20c:指定输出category的名称，最小的宽度是20，如果category的名称小于20的话，"-"号指定左对齐。
3)%.30c:指定输出category的名称，最大的宽度是30，如果category的名称大于30的话，就会将左边多出的字符截掉，但小于30的话也不会有空格。
4)%20.30c:如果category的名称小于20就补空格，并且右对齐，如果其名称长于30字符，就从左边较远输出的字符截掉。
```

代码中初始化Logger: 
```plain
- 1）在程序中调用BasicConfigurator.configure()方法：给根记录器增加一个ConsoleAppender，输出格式通过PatternLayout设为"%-4r [%t] %-5p %c %x - %m%n"，还有根记录器的默认级别是Level.DEBUG. 
- 2）配置放在文件里，通过命令行参数传递文件名字，通过PropertyConfigurator.configure(args[x])解析并配置；
- 3）配置放在文件里，通过环境变量传递文件名等信息，利用log4j默认的初始化过程解析并配置；
- 4）配置放在文件里，通过应用服务器配置传递文件名等信息，利用一个特殊的servlet来完成配置。
```


为不同的 Appender 设置日志输出级别：

- 当调试系统时，我们往往注意的只是异常级别的日志输出，但是通常所有级别的输出都是放在一个文件里的，如果日志输出的级别是BUG！？那就慢慢去找吧。
- 这时我们也许会想要是能把异常信息单独输出到一个文件里该多好啊。当然可以，Log4j已经提供了这样的功能，我们只需要在配置中修改Appender的Threshold 就能实现,比如下面的例子：
```
 ### set log levels ###  log4j.properties
log4j.rootLogger = debug , stdout , D , E
  
### 输出到控制台 ###  
log4j.appender.stdout = org.apache.log4j.ConsoleAppender
log4j.appender.stdout.Target = System.out
log4j.appender.stdout.layout = org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern = %d{ABSOLUTE} %5p %c{ 1 }:%L - %m%n
  
### 输出到日志文件 ###  
log4j.appender.D = org.apache.log4j.DailyRollingFileAppender
log4j.appender.D.File = logs/log.log
log4j.appender.D.Append = true
log4j.appender.D.Threshold = DEBUG ## 输出DEBUG级别以上的日志
log4j.appender.D.layout = org.apache.log4j.PatternLayout
log4j.appender.D.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss} [ %t:%r ] - [ %p ] %m%n
  
### 保存异常信息到单独文件 ###  
log4j.appender.E = org.apache.log4j.DailyRollingFileAppender
log4j.appender.E.File = logs/error.log ## 异常日志文件名  
log4j.appender.E.Append = true
log4j.appender.E.Threshold = ERROR ## 只输出ERROR级别以上的日志!!!
log4j.appender.E.layout = org.apache.log4j.PatternLayout
log4j.appender.E.layout.ConversionPattern = %-d{yyyy-MM-dd HH:mm:ss} [ %t:%r ] - [ %p ] %m%n
```
