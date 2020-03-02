package com.nhlstenden.amazonsimulatie.base;

import java.util.ArrayList;

public class Grid {
    public ArrayList<Tile> positions = new ArrayList<Tile>();

    public Grid(){
        for(int i=0; i < 31; i++){
            positions.add(new Tile(i,i));
        }
    }
}
