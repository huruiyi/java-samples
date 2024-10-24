package com.example.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
import lombok.Data;

@Data
@Named("messageProvider")
public class ConfigurableMessageProvider implements MessageProvider {

  private String message = "Default message";

  public ConfigurableMessageProvider() {
  }

  @Inject
  @Named("message")
  public ConfigurableMessageProvider(String message) {
    this.message = message;
  }

}
