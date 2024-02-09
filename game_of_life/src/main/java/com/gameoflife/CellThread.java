package com.gameoflife;

public class CellThread implements Runnable{

    CellBuffer buzon;
    private int row;

    public CellThread(int row){

        this.row = row;
        this.buzon = new CellBuffer(row+1);

    }



    public void turnDead(){

    }

    

    public void run(){
        
        System.out.println("My row is " + this.row);

    }

    
    
}
