package com.packtpub.angularspringbook.greeting;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class CommandLineRunnerInitializer implements CommandLineRunner {

  @Override
  public void run(String... args) throws Exception {
    System.out.println("CommandLineRunnerInitializer::");
  }
}
