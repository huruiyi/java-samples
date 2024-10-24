package vip.fairy;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.core.Filter;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.*;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;

import java.io.IOException;

/**
 * <a href="https://www.baeldung.com/log4j2-programmatic-config">log4j2-programmatic-config</a>
 * <p>
 * <a href="https://github.com/eugenp/tutorials/tree/master/logging-modules/log4j2">log4j2</a>
 */
public class ProgrammaticConfiguration {

    public static void main(String[] args) throws IOException {
        ConfigurationBuilder<BuiltConfiguration> builder = ConfigurationBuilderFactory.newConfigurationBuilder();

        LayoutComponentBuilder standard = builder.newLayout("PatternLayout");
        standard.addAttribute("pattern", "%d [%t] %-5level: %msg%n%throwable");

        FilterComponentBuilder flow = builder.newFilter("MarkerFilter", Filter.Result.ACCEPT, Filter.Result.DENY);
        flow.addAttribute("marker", "FLOW");

        ComponentBuilder cronTriggeringPolicy = builder.newComponent("CronTriggeringPolicy").addAttribute("schedule", "0 0 0 * * ?");
        ComponentBuilder sizeBasedTriggeringPolicy = builder.newComponent("SizeBasedTriggeringPolicy").addAttribute("size", "100M");
        ComponentBuilder triggeringPolicies = builder.newComponent("Policies").addComponent(cronTriggeringPolicy).addComponent(sizeBasedTriggeringPolicy);

        AppenderComponentBuilder console = builder.newAppender("stdout", "Console");
        console.add(standard);
        console.add(flow);
        builder.add(console);

        AppenderComponentBuilder rollingFile = builder.newAppender("rolling", "RollingFile");
        rollingFile.addAttribute("fileName", "rolling.log");
        rollingFile.addAttribute("filePattern", "rolling-%d{MM-dd-yy}.log.gz");
        rollingFile.add(standard);
        rollingFile.addComponent(triggeringPolicies);
        builder.add(rollingFile);

        AppenderComponentBuilder file = builder.newAppender("log", "File");
        file.addAttribute("fileName", "target/logging.log");
        file.add(standard);
        builder.add(file);

        LoggerComponentBuilder logger = builder.newLogger("com", Level.DEBUG);
        logger.add(builder.newAppenderRef("log"));
        logger.addAttribute("additivity", false);
        builder.add(logger);

        RootLoggerComponentBuilder rootLogger = builder.newRootLogger(Level.ERROR);
        rootLogger.add(builder.newAppenderRef("stdout"));
        builder.add(rootLogger);

        builder.writeXmlConfiguration(System.out);

        //Now that we are fully configured, let’s tell Log4j 2 to use our configuration:
        Configurator.initialize(builder.build());
    }
}
