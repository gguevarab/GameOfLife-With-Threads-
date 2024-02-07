package com.gameoflife;

public class CellBuffer {

    private int n_alive_homies = 0;


    public void declareAliveToHomie(){

        this.n_alive_homies += 1;

    }

    public int getAliveHomies(){

        return this.n_alive_homies;

    }


    public CellBuffer(){


        
    }
    
}
