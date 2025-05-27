package com.example.jsr330;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import lombok.Data;

@Data
@Named("messageRenderer")
@Singleton
public class StandardOutMessageRenderer implements MessageRenderer {

  @Inject
  @Named("messageProvider")
  private MessageProvider messageProvider;

  public void render() {
    if (messageProvider == null) {
      throw new RuntimeException("You must set the property messageProvider of class:" + StandardOutMessageRenderer.class.getName());
    }

    System.out.println(messageProvider.getMessage());
  }


}
