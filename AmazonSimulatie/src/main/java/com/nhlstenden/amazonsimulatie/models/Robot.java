package com.nhlstenden.amazonsimulatie.models;


import com.nhlstenden.amazonsimulatie.base.Map;

import java.util.ArrayList;
import java.util.Random;
import java.util.Set;
import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public class Robot implements Object3D, Updatable {
    private UUID uuid;

    public double x = 0;
    private double y = 0.01;
    public double z = 0;

    public int currentPosition = 1;
    public int destination;

    public double destinationX = 0;
    public double destinationZ = 0;

    public ArrayList<Stelling> stellings = new ArrayList<Stelling>();

    private com.nhlstenden.amazonsimulatie.base.Map map;
    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    Random r = new Random();
    public Stelling assignedStelling;
    public Robot(Map map) {
        this.uuid = UUID.randomUUID();
        this.map = map;
    }

    /*
     * Deze update methode wordt door de World aangeroepen wanneer de
     * World zelf geupdate wordt. Dit betekent dat elk object, ook deze
     * robot, in de 3D wereld steeds een beetje tijd krijgt om een update
     * uit te voeren. In de updatemethode hieronder schrijf je dus de code
     * die de robot steeds uitvoert (bijvoorbeeld positieveranderingen). Wanneer
     * de methode true teruggeeft (zoals in het voorbeeld), betekent dit dat
     * er inderdaad iets veranderd is en dat deze nieuwe informatie naar de views
     * moet worden gestuurd. Wordt false teruggegeven, dan betekent dit dat er niks
     * is veranderd, en de informatie hoeft dus niet naar de views te worden gestuurd.
     * (Omdat de informatie niet veranderd is, is deze dus ook nog steeds hetzelfde als
     * in de view)
     */
    @Override
    public boolean update() {
        MoveToPosition(destinationX,destinationZ);
        ChangeDirection();
        GrabStelling();
        return true;
    }

    /*
       Deze methode zorgt er voor dat wanneer de afstand tussen een robot en zijn aangewezen stelling dusdanig klein is
        dat hij de positie van de stelling gelijk maakt aan de robot.
    */
    private void GrabStelling() {
        System.out.println(Math.sqrt(Math.pow(x - z,2) + Math.pow(assignedStelling.getX() - assignedStelling.getZ(), 2)));
            if (Math.sqrt(Math.pow(x - z,2) + Math.pow(assignedStelling.getX() - assignedStelling.getZ(), 2))< 4    ) {
                assignedStelling.setX(x);
                assignedStelling.setZ(z);
            }
    }

    /*
    Deze methode laat de robot een willekeurige richting kiezen door de afstand te vergelijken tussen de robot en de X en Z van zijn doel.
     */
    private void ChangeDirection() {
            if (Math.sqrt(Math.pow((destinationX * 3) -1.5,2)  - Math.pow(x,2)) < 3 && Math.sqrt(Math.pow((destinationZ * 3) - 1.5,2) - Math.pow(z,2)) < 5) {
                SetDestinationZ(r.nextInt((10 - 1) + 1) + 1);
                SetDestinationX(r.nextInt((10 - 1) + 1) + 1);
            }
            if (Math.sqrt(Math.pow((destinationX * 3) -1.5,2)  - Math.pow(x,2)) > 3 && Math.sqrt(Math.pow((destinationZ * 3) - 1.5,2) - Math.pow(z,2)) < 5) {
                SetDestinationZ(r.nextInt((10 - 1) + 1) + 1);
            }
            if (Math.sqrt(Math.pow((destinationX * 3) -1.5,2)  - Math.pow(x,2)) < 3 && Math.sqrt(Math.pow((destinationZ * 3) - 1.5,2) - Math.pow(z,2)) > 5) {
                SetDestinationX(r.nextInt((10 - 1) + 1) + 1);
            }

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
        return Robot.class.getSimpleName().toLowerCase();
    }
    /*
        Deze methode laat de robot bewegen naar een X en Z positie
    */
    public void MoveToPosition(double destinationX, double destinationZ){
        if(x < (destinationX * 3) - 1.5){
            x += 0.1;
        }
        if(z < (destinationZ * 3) - 1.5){
            z += 0.1;
        }
        if(x > (destinationX * 3) - 1.5){
            x -= 0.1;
        }
        if(z > (destinationZ * 3) - 1.5){
            z -= 0.1;
        }


    }

    @Override
    public double getX() {
        return this.x;
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

    /*
        Setters
    */
    public double SetDestinationX(double x){
        return destinationX = x;
    }
    public double SetDestinationZ(double z){
        return destinationZ = z;
    }
    /*
        Getters
     */
    public double getDestinationX() {
        return destinationX;
    }
    public double getDestinationZ(){
        return destinationZ;
    }
    /*
    Setter
     */
    public void SetMap(Map map){
        this.map = map;
    }
}