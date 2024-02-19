package com.gameoflife;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

//Cell class
public class CellThread extends Thread{

    private static CyclicBarrier barrier;

    CellBuffer mailbox;
    CellThread[][] cellMap;
    private boolean isAlive;
    private int[] coordinates;
    private int n;

	public static void initializeBarrier(int barrierSize) {

		barrier = new CyclicBarrier(barrierSize);

	}

    public CellThread(int[] coordinates, boolean status){

        this.coordinates = coordinates;
        this.isAlive = status;
        

    }

    
    public void setMap(CellThread[][] cellMap){

        this.cellMap = cellMap;
		this.n = cellMap.length;

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
		
		try {
			barrier.await();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (BrokenBarrierException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

    }

    
    
}
