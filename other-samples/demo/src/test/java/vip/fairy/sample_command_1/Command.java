package vip.fairy.sample_command_1;

public abstract class Command {

  protected Receiver receiver;

  public Command(Receiver receiver) {
    this.receiver = receiver;
  }

  public abstract void Execute();
}
