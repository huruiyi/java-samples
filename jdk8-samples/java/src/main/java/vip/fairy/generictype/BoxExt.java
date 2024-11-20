package vip.fairy.generictype;

public class BoxExt<T extends Employee> {

  T element;

  T getElement() {
    return this.element;
  }

  void setElement(T valueOfElement) {
    this.element = valueOfElement;
  }
}
