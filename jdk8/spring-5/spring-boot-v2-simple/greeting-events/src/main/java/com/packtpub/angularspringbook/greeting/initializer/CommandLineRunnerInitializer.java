package com.packtpub.angularspringbook.greeting.initializer;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
class CommandLineRunnerInitializer implements CommandLineRunner {

  @Override
  public void run(String... args) {
    System.out.println("CommandLineRunnerInitializer::");
  }
}
