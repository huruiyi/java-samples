package com.example.demoM;

public interface SequenceDao {

  Sequence getSequence(String sequenceId);

  int getNextValue(String sequenceId);
} 
