package com.example.bean_aliases.annotated;

import org.springframework.core.annotation.AliasFor;

@Award
public @interface Trophy {

  @AliasFor(annotation = Award.class, attribute = "value")
  String[] name() default {};
}
