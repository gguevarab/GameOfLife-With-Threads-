package com.gameoflife;

import java.util.concurrent.CyclicBarrier;

//Cell class
public class CellThread extends Thread{

    private static CyclicBarrier barrier = new CyclicBarrier(0);

    CellBuffer mailbox;
    CellThread[][] cellMap;
    private boolean isAlive;
    private int[] coordinates;
    private int n;

    public CellThread(int[] coordinates, boolean status){

        this.coordinates = coordinates;
        this.isAlive = status;
        this.n = cellMap.length;
        

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

    private int calculateAdjacents() 
    {
    	//See if cell is in a edge or a border
    	if (this.coordinates[0]==0) 
    	{
    		if (this.coordinates[1]==0) 
    		{
    			//[0,0]
    	    	return 3;
    			
    		}
    		else if (this.coordinates[1]==n) 
    		{
    			//[0,n]
    	    	return 3;
    			
    		}
    		else 
    		{
    			//Border [0,x]
    	    	return 5;
    			
    		}
    	}
    	else if (this.coordinates[0]==n) 
    	{
    		if (this.coordinates[1]==n) 
    		{
    			//[n,n]
    	    	return 3;
    			
    		}
    		else if (this.coordinates[1]==0) 
    		{
    			//[n,0]
    	    	return 3;
    			
    		}
    		else 
    		{
    			//Border [n,x]
    	    	return 5;
    		}
    	}
    	else if (this.coordinates[1]==0) 
    	{
			//Border [x,0]
	    	return 5;
    	}
    	else if (this.coordinates[1]==n) 
    	{
			//Border [x,n]
	    	return 5;
    	}
    	return 8;
    }

    public void run(){
        
        System.out.println("My row is " + this.coordinates[0]);
        this.mailbox = new CellBuffer(coordinates[0]+1, calculateAdjacents());

    }

    
    
}
