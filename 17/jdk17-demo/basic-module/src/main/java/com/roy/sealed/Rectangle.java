package com.roy.sealed;

public sealed class Rectangle extends Shape permits FilledRectangle {
    @Override
    public int lines() {
        return 3;
    }
}
