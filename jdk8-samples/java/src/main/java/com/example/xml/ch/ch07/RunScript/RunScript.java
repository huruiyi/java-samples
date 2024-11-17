package com.example.xml.ch.ch07.RunScript;

import static java.lang.System.err;

import java.io.FileReader;
import java.io.IOException;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineFactory;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import org.junit.jupiter.api.Test;

public class RunScript {

  public static void main(String[] args) {
    if (args.length != 1) {
      err.println("usage: java RunScript script");
      return;
    }
    ScriptEngineManager manager = new ScriptEngineManager();
    ScriptEngine engine = manager.getEngineByName("nashorn");
    try {
      engine.eval(new FileReader(args[0]));
    } catch (ScriptException se) {
      err.println(se.getMessage());
    } catch (IOException ioe) {
      err.println(ioe.getMessage());
    }
  }

  @Test
  void test1() {
    ScriptEngineManager mgr = new ScriptEngineManager();

    for (ScriptEngineFactory factory : mgr.getEngineFactories()) {
      String engineName = factory.getEngineName();
      String languageName = factory.getLanguageName();
      String version = factory.getLanguageVersion();
      System.out.println("Engine name: " + engineName + " Language: " + languageName + " Version: " + version);
    }
  }

  @Test
  void test2() {
    ScriptEngineManager mgr = new ScriptEngineManager();
    ScriptEngine jsEngine = mgr.getEngineByName("JavaScript");

    try {
      jsEngine.eval("print('Hello JavaScript in Java')");
    } catch (ScriptException ex) {
      ex.printStackTrace();
    }
  }

}
