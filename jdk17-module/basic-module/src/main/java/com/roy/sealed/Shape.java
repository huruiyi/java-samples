package com.roy.sealed;

public sealed abstract class Shape permits Circle, Rectangle, Square {

    public abstract int lines();
}
