package com.nhlstenden.amazonsimulatie.base;

import java.util.ArrayList;

public class Map {
    Grid grid = new Grid();
    public ArrayList<Tile> nonWalkable = new ArrayList<Tile>();
    public Map(){
        // hier een lijst van Niet-wandelbare tegels
        for (int i=0; i < grid.positions.size(); i++){
            double z = grid.positions.get(i).getZ();
            if(z==2){
                nonWalkable.add(grid.positions.get(i));
            }
            if(z==4){
                nonWalkable.add(grid.positions.get(i));
            }
            if(z==6){
                nonWalkable.add(grid.positions.get(i));
            }
        }

    }
}
