package com.gameoflife;

//Buffer class
public class CellBuffer {

    private int nAlive = 0;
    private int capacity;


    public void declareAlive(){

    }

    public int getAlive(){

        return this.nAlive;

    }


    public CellBuffer(int capacity){

        this.capacity = capacity;
        System.out.println("I'm a mailbox with a capacity of " + capacity);
        
    }
    
}
