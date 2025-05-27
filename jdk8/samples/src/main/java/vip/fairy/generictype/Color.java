package vip.fairy.generictype;

public class Color {

  String color;

  Color(String color) {
    this.color = color;
  }

  String getColor() {
    return this.color;
  }

  @Override
  public boolean equals(Object o) {
    return (((Color) o).color).equals(this.color);
  }

}
