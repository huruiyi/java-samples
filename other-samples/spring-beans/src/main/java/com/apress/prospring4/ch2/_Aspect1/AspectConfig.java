package com.apress.prospring4.ch2._Aspect1;


import com.apress.prospring4.ch2._Aspect1.service.Animal;
import com.apress.prospring4.ch2._Aspect1.service.impl.FemaleAnimal;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.DeclareParents;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {

  //"+"表示person的所有子类；defaultImpl 表示默认需要添加的新的类
  @DeclareParents(value = "com.apress.prospring4.ch2._Aspect1.service.Person+", defaultImpl = FemaleAnimal.class)
  public Animal animal;
}
