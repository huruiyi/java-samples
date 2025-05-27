package com.example.application_context_aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.GenericApplicationContext;

public class ShutdownHookBean implements ApplicationContextAware {

  private ApplicationContext ctx;

  /**
   * {@link ApplicationContextAware#setApplicationContext(ApplicationContext)}
   */
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {

    if (ctx instanceof GenericApplicationContext) {
      ((GenericApplicationContext) ctx).registerShutdownHook();
    }
  }
}
