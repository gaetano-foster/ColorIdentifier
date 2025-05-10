package com.github.coloridentifier.graphing;

import java.awt.*;

public class DataPoint {
    public double x, y;
    public char symbol;
    public Color color;

    public DataPoint(double x, double y) {
        this.x = x;
        this.y = y;
        this.symbol = '0';
        this.color = Color.BLACK;
    }

    public DataPoint(double x, double y, char symbol) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.color = Color.BLACK;
    }

    public DataPoint(double x, double y, Color color) {
        this.x = x;
        this.y = y;
        this.symbol = '0';
        this.color = color;
    }

    public DataPoint(double x, double y, char symbol, Color color) {
        this.x = x;
        this.y = y;
        this.symbol = symbol;
        this.color = color;
    }
}
