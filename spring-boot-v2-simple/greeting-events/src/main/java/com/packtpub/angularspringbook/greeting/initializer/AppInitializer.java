package com.packtpub.angularspringbook.greeting;

import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
class AppInitializer {

  @EventListener(ApplicationReadyEvent.class)
  public void onApplicationReadyEvent(ApplicationReadyEvent event) {
    System.out.println("AppInitializer:: on ApplicationReadyEvent");
  }

}
