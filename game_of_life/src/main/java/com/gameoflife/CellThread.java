package com.gameoflife;

import java.util.concurrent.CyclicBarrier;

//Cell class
public class CellThread extends Thread{

    private static CyclicBarrier barrier = new CyclicBarrier(0);

    CellBuffer mailbox;
    CellThread[][] cellMap;
    private boolean isAlive;
    private int[] coordinates;

    public CellThread(int[] coordinates, boolean status){

        this.coordinates = coordinates;
        this.isAlive = status;

    }


    public void setMap(CellThread[][] cellMap){

        this.cellMap = cellMap;

    }



    public void turnDead(){

        this.isAlive = false;

    }


    public boolean getStatus(){

        return this.isAlive;

    }

    

    public void run(){
        
        System.out.println("My row is " + this.coordinates[0]);
        this.mailbox = new CellBuffer(coordinates[0]+1);

    }

    
    
}
