package com.nhlstenden.amazonsimulatie.base;

public class Tile {
    private double x,y;
    public int position;
    public Tile(double x, double y ){
        this.x = x;
        this.y = y;
    }

    public double getY() {
        return y;
    }

    public double getX() {
        return x;
    }
}
