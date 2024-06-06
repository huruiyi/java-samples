package com.example.aop_namespace;


import com.example.model.Documentarist;
import com.example.model.Guitar;

public class NewDocumentarist extends Documentarist {

  @Override
  public void execute() {
    guitarist.sing();
    Guitar guitar = new Guitar();
    guitar.setBrand("Gibson");
    guitarist.sing(guitar);
    //guitarist.sing(new Guitar());
    guitarist.talk();
  }

}
