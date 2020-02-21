package com.nhlstenden.amazonsimulatie.base;

public class Tile {
    private double x,z;

    public Tile(double x, double z ){
        this.x = x;
        this.z = z;
    }

    public double getZ() {
        return z;
    }

    public double getX() {
        return x;
    }
}
