package com.gameoflife;

public class CellBuffer {

    private int n_alive_homies = 0;
    private int capacity;


    public void declareAliveToHomies(){

        this.n_alive_homies += 1;

    }

    public int getAliveHomies(){

        return this.n_alive_homies;

    }


    public CellBuffer(int capacity){

        this.capacity = capacity;
        System.out.println("I'm a mailbox with a capacity of " + capacity);
        
    }
    
}
