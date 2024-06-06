package com.example.model.introduction;

import org.springframework.aop.support.DefaultIntroductionAdvisor;

public class IsModifiedAdvisor extends DefaultIntroductionAdvisor {

  public IsModifiedAdvisor() {
    super(new IsModifiedMixin());
  }
}
