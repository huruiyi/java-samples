package com.apress.prospring4.ch2.customScope;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.config.Scope;

public class ThreadScope implements Scope {

  private final ThreadLocal<Map<String, Object>> threadScope = new ThreadLocal<Map<String, Object>>() {
    @Override
    protected Map<String, Object> initialValue() {
      return new HashMap<String, Object>();
    }
  };

  public Object get(String name, ObjectFactory<?> objectFactory) {
    Map<String, Object> scope = threadScope.get();
    Object obj = scope.get(name);
    System.out.println("Get " + name);
    if (obj == null) {
      System.out.println("Not exists " + name);
      obj = objectFactory.getObject();
      scope.put(name, obj);
    }
    return obj;
  }

  public Object remove(String name) {
    Map<String, Object> scope = threadScope.get();
    return scope.remove(name);
  }

  public String getConversationId() {
    return null;
  }

  public void registerDestructionCallback(String arg0, Runnable arg1) {
  }

  public Object resolveContextualObject(String arg0) {
    return null;
  }

}
