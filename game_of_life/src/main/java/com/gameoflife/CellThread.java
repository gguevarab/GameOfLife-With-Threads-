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

    public CellThread(int[] coordinates, boolean status, int mapSize){

        this.coordinates = coordinates;
        this.isAlive = status;      
		this.mailbox = new CellBuffer(coordinates[0]+1, calculateAdjacents(mapSize), status);  

    }

    
    public void setMap(CellThread[][] cellMap){

        this.cellMap = cellMap;
		this.n = cellMap.length;

    }


    public boolean getStatus(){

        return this.isAlive;

    }

    private int calculateAdjacents(int mapSize) 
    {
		int nAdjacents = 0;

		for(int x = -1; x < 2; x++){
			for(int y = -1; y < 2; y++){
				if(x == 0 && y == 0){
					continue;
				}
				else if(coordinates[0] + x < 0 || coordinates[1] + y < 0){
					continue;
				} else{

					try{
						if(coordinates[0] + x < mapSize && coordinates[1] + y < mapSize){
							nAdjacents++;
						}
						
					} catch(Exception e) {
						
					}

				}
				
			}
		}

		return nAdjacents;
		/*
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
		*/
    }

    public void run(){

		//System.out.println("My row is " + coordinates[0] + " at column " + coordinates[1]);

		ConsumerCell consumer = new ConsumerCell(this.mailbox);
		ProductorCell productor = new ProductorCell(this.isAlive, this.coordinates, this.cellMap);

		consumer.start();
		productor.start();

		try {
			productor.join();
			consumer.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*/
		if(tempBufferData[0] > 0 && tempBufferData[0] < 4){
			this.isAlive = true;
		} else {
			this.isAlive = false;
		}
		*/

		this.isAlive = mailbox.getAliveCell();

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
