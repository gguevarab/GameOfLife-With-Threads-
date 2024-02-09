package com.gameoflife;

public class CellThread implements Runnable{

    CellBuffer mailbox;
    private int[] coordinates;

    public CellThread(int[] coordinates){

        this.coordinates = coordinates;

    }



    public void turnDead(){

    }

    

    public void run(){
        
        System.out.println("My row is " + this.coordinates[0]);
        this.mailbox = new CellBuffer(coordinates[0]+1);

    }

    
    
}
