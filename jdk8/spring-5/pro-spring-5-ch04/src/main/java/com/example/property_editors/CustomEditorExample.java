package com.example.property_editors;

import lombok.Data;
import org.springframework.context.support.GenericXmlApplicationContext;

@Data
public class CustomEditorExample {

  private FullName name;

  public static void main(String... args) {
    GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
    ctx.load("classpath:spring/property-editors-02.xml");
    ctx.refresh();

    CustomEditorExample bean = (CustomEditorExample) ctx.getBean("exampleBean");

    System.out.println(bean.getName());

    ctx.close();
  }
}
