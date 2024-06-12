package com.packtpub.angularspringbook.greeting;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class GreetingController {

  private final Logger LOG = LogManager.getLogger(GreetingController.class);

  @GetMapping
  public String sayHello() {
    LOG.error("print ERROR message::");
    LOG.info("print INFO message::");
    LOG.warn("print WARN message::");
    LOG.debug("print DEBUG message::");
    LOG.trace("print TRACE message::");

    return "Hello Spring Boot";
  }
}
