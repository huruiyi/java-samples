package com.apress.prospring4.ch2.EL;

public class Item {

  private String good;
  private double weight;

  public String getGood() {
    return good;
  }

  public void setGood(String good) {
    this.good = good;
  }

  public double getWeight() {
    return weight;
  }

  public void setWeight(double weight) {
    this.weight = weight;
  }

  @Override
  public String toString() {
    return "Item [good=" + good + ", weight=" + weight + "]";
  }
}
