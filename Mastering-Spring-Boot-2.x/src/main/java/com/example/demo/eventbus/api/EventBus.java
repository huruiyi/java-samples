package com.example.demo.eventbus.api;

import com.example.demo.eventbus.domain.Event;

public interface EventBus {
  void publish(Event event);
  Event receive();
}
