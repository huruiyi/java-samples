package vip.fairy.sample_command_1;

public class Invoker {

  private Command command;

  public void setCommand(Command command) {
    this.command = command;
  }

  public void executeCommand() {
    command.Execute();
  }
}
