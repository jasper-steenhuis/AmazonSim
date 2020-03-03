package com.nhlstenden.amazonsimulatie.base;

import java.util.ArrayList;

public class Map {
    Grid grid = new Grid();
    public ArrayList<Tile> storage = new ArrayList<Tile>();
    public Map(){
        // hier een lijst van Niet-wandelbare tegels
        for (int i=0; i < grid.positions.size(); i++){
            //if(grid.positions.get(i).getZ() % 2 != 0){
                storage.add(grid.positions.get(i));
            //}
        }
        System.out.println(storage.size());

    }
}
