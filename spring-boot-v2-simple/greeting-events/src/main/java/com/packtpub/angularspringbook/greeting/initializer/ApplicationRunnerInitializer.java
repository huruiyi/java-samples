package com.packtpub.angularspringbook.greeting;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
class ApplicationRunnerInitializer implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args) throws Exception {
    for (String optionName : args.getOptionNames()) {
      System.out.println("Option[" + optionName + ":" + args.getOptionValues(optionName) + "]");
    }

    System.out.println("ApplicationRunnerInitializer::");
  }
}
