package com.nhlstenden.amazonsimulatie.models;

import com.nhlstenden.amazonsimulatie.base.Map;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;


/*
 * Deze class stelt een stelling voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public class Stelling implements Object3D, Updatable {
    private UUID uuid;

    public double x = 0;
    private double y = 0.01;
    public double z = 0;

    public double destinationX = x;
    public double destinationZ = z;

    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    private ArrayList<Robot> robots = new ArrayList<Robot>();

    private com.nhlstenden.amazonsimulatie.base.Map map;
    public Stelling(Map map, ArrayList<Robot> robots) {
        this.uuid = UUID.randomUUID();
        this.robots = robots;
    }





    @Override
    public boolean update() {

        return true;
    }



    @Override
    public String getUUID() {
        return this.uuid.toString();
    }

    @Override
    public String getType() {
        /*
         * Dit onderdeel wordt gebruikt om het type van dit object als stringwaarde terug
         * te kunnen geven. Het moet een stringwaarde zijn omdat deze informatie nodig
         * is op de client, en die verstuurd moet kunnen worden naar de browser. In de
         * javascript code wordt dit dan weer verder afgehandeld.
         */
        return Stelling.class.getSimpleName().toLowerCase();
    }

    @Override
    public double getX() {
        return this.x;
    }

    public double setX(double x) {
        return this.x = x;
    }
    public double setZ(double z) {
        return this.z = z;
    }

    @Override
    public double getY() {
        return this.y;
    }

    @Override
    public double getZ() {
        return this.z;
    }

    @Override
    public double getRotationX() {
        return this.rotationX;
    }

    @Override
    public double getRotationY() {
        return this.rotationY;
    }

    @Override
    public double getRotationZ() {
        return this.rotationZ;
    }

}
