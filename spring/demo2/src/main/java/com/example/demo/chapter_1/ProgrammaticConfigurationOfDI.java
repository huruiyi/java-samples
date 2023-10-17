package com.example.demo.chapter_1;

import com.example.demo.chapter_1.business_components.ComplexLogicService;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProgrammaticConfigurationOfDI {

  private final ComplexLogicService complexLogicService;

  @Autowired
  public ProgrammaticConfigurationOfDI(
      ComplexLogicService complexLogicService) {
    this.complexLogicService = complexLogicService;
  }

  @PostConstruct
  public void init() {
    complexLogicService.calculateAndSend(100);
  }
}
