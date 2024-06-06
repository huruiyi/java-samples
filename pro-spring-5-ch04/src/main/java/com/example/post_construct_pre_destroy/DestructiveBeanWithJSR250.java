package com.example.post_construct_pre_destroy;

import java.io.File;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.springframework.context.support.GenericXmlApplicationContext;

public class DestructiveBeanWithJSR250 {

  private File file;
  private String filePath;

  public void setFilePath(String filePath) {
    this.filePath = filePath;
  }

  @PostConstruct
  public void afterPropertiesSet() throws Exception {
    System.out.println("Initializing Bean");

    if (filePath == null) {
      throw new IllegalArgumentException("You must specify the filePath property of " + DestructiveBeanWithJSR250.class);
    }

    this.file = new File(filePath);
    this.file.createNewFile();

    System.out.println("File exists: " + file.exists());
  }

  @PreDestroy
  public void destroy() {
    System.out.println("Destroying Bean");

    if (!file.delete()) {
      System.err.println("ERROR: failed to delete file.");
    }

    System.out.println("File exists: " + file.exists());
  }


  public static void main(String... args) throws Exception {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/post-construct-pre-destroy.xml");
    ctx.refresh();

    DestructiveBeanWithJSR250 bean = (DestructiveBeanWithJSR250) ctx.getBean("destructiveBean");
    System.out.println(bean.filePath);

    System.out.println("Calling destroy()");
    ctx.close();
    System.out.println("Called destroy()");
  }
}
