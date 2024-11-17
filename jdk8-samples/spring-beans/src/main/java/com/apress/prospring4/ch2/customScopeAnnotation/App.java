package com.apress.prospring4.ch2.customScopeAnnotation;

import com.apress.prospring4.ch2.customScopeAnnotation.service.MessageService;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class App {

  public static void main(String[] args) throws ExecutionException, InterruptedException {
    test2();
  }

  private static void test1() {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    MessageService messageService = context.getBean(MessageService.class);
    messageService.getMessage();

    MessageService messageService2 = context.getBean(MessageService.class);
    messageService2.getMessage();
  }

  private static void test2() throws ExecutionException, InterruptedException {
    ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);

    CompletableFuture<String> task1 = CompletableFuture.supplyAsync(() -> {
      MessageService messageService = context.getBean(MessageService.class);
      messageService.getMessage();

      MessageService messageService2 = context.getBean(MessageService.class);
      messageService2.getMessage();
      return "result";
    });

    CompletableFuture<String> task2 = CompletableFuture.supplyAsync(() -> {
      MessageService messageService = context.getBean(MessageService.class);
      messageService.getMessage();

      MessageService messageService2 = context.getBean(MessageService.class);
      messageService2.getMessage();
      return "result";
    });

    task1.get();
    task2.get();
  }
}
