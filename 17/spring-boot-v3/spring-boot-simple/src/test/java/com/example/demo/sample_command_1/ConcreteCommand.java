package com.example.demo.sample_command_1;

public class ConcreteCommand extends Command {

  public ConcreteCommand(Receiver receiver) {
    super(receiver);
  }

  @Override
  public void Execute() {
    receiver.Action();
  }

}
