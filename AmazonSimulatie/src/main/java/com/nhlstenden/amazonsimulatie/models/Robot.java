package com.nhlstenden.amazonsimulatie.models;

import java.util.Random;
import java.util.UUID;

/*
 * Deze class stelt een robot voor. Hij impelementeerd de class Object3D, omdat het ook een
 * 3D object is. Ook implementeerd deze class de interface Updatable. Dit is omdat
 * een robot geupdate kan worden binnen de 3D wereld om zich zo voort te bewegen.
 */
public class Robot implements Object3D, Updatable {
    private UUID uuid;

    private double x = 0;
    private double y = 0.01;
    private double z = 0;

    public int currentPosition = 1;
    public int destination;




    private double rotationX = 0;
    private double rotationY = 0;
    private double rotationZ = 0;

    public Robot() {
        this.uuid = UUID.randomUUID();
    }



    public void MoveTo() {


            if (this.x < x){
                this.x += 0.1;
            }
            if (this.z < z){
                this.z += 0.1;
            }

            if (this.x > x){
                this.x -= 0.1;
            }
            if (this.z > z){
                this.z -= 0.1;
            }

        }


    public void GoToDestination(int position) {
        this.destination = position;
    }
    public void SetNextPosition(int position){
        this.currentPosition = position;
    }
    public int GetRandomStartPosition(){
        int[] nums = {101,201,301,401};
        Random r = new Random();
        int randomNumber = r.nextInt(nums.length);
        System.out.println(nums[randomNumber]);
        return nums[randomNumber];
    }
    public void SetPosition(int position) {
        this.destination = position;

    }

    public int GetPosition(){
        return this.currentPosition;
    }
    public int GetDestination(){
        return this.destination;
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
        this.MoveTo();
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
        return Robot.class.getSimpleName().toLowerCase();
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
}