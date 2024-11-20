package vip.fairy.demoM;

public interface SequenceDao {

  public Sequence getSequence(String sequenceId);

  public int getNextValue(String sequenceId);
} 
