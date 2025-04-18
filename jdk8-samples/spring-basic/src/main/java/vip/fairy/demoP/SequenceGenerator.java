package vip.fairy.demoP;

import org.springframework.beans.factory.annotation.Autowired;

public class SequenceGenerator {

  @Autowired
  private PrefixGenerator prefixGenerator;
  private String suffix;
  private int initial;
  private int counter;

  public SequenceGenerator() {
  }

  public SequenceGenerator(PrefixGenerator prefixGenerator, String suffix, int initial) {
    this.prefixGenerator = prefixGenerator;
    this.suffix = suffix;
    this.initial = initial;
  }

  public void setPrefixGenerator(PrefixGenerator prefixGenerator) {
    this.prefixGenerator = prefixGenerator;
  }

  public void setSuffix(String suffix) {
    this.suffix = suffix;
  }

  public void setInitial(int initial) {
    this.initial = initial;
  }

  public synchronized String getSequence() {
    StringBuilder buffer = new StringBuilder();
    buffer.append(prefixGenerator.getPrefix());
    buffer.append(initial + counter++);
    buffer.append(suffix);
    return buffer.toString();
  }
}
