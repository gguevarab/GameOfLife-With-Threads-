package com.gameoflife;

public class CellBuffer {

    private int n_alive_homies = 0;
    private int capacity;


    public void declareAliveToHomie(){

        this.n_alive_homies += 1;

    }

    public int getAliveHomies(){

        return this.n_alive_homies;

    }


    public CellBuffer(int capacity){

        this.capacity = capacity;
        
    }
    
}
