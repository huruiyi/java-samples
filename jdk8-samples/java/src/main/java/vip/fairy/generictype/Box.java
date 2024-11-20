package vip.fairy.generictype;

public class Box<T> {

  T element;

  T getElement() {
    return this.element;
  }

  void setElement(T valueOfElement) {
    this.element = valueOfElement;
  }
}
