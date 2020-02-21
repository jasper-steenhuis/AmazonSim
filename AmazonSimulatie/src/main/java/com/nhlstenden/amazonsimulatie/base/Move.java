package com.nhlstenden.amazonsimulatie.base;

public class Move implements Command {
    double currentX;
    double currentZ;

    double destinationX,destinationZ;

    public Move(double currentX, double currentZ, double destinationX, double destinationZ) {
        this.currentX = currentX;
        this.currentZ = currentZ;
        this.destinationX = destinationX;
        this.destinationZ = destinationZ;
    }

    @Override
    public void execute() {
       if(currentX < destinationX){
           currentX +=0.1;
       }
       if (currentZ < destinationZ){
           currentZ +=0.1;
       }

    }

    @Override
    public String getName() {
        return "Move";
    }

    public String GetDestination(){
        return destinationX + " " + destinationZ;
    }

}
