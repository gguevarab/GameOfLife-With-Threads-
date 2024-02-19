package com.gameoflife;

//Buffer class
public class CellBuffer {

    private int nAlive = 0;
    private int nDead = 0;
    private int nAdjacents = 0;
    private int capacity;
    private Semáforo semaforo;

    public synchronized void escribir(boolean estado){
        semaforo.p();
    	if(estado) 
    	{
        	this.nAlive++;
    	}
    	else 
    	{
        	this.nDead++;
    	}
    	this.nAdjacents--;
    }
    
    public synchronized int leer() 
    {
        semaforo.v();
    	return this.nAlive;
    }

    public int getAlive(){

        return this.nAlive;

    }

    public int getDead(){

        return this.nDead;

    }

    public int getNAdjacents(){

        return this.nAdjacents;

    }

    public int getCapacity(){

        return this.capacity;

    }


    public CellBuffer(int capacity, int adjacents){

        this.nAdjacents = adjacents;
        this.capacity = capacity;
        this.semaforo  = new Semáforo(capacity);
        System.out.println("I'm a mailbox with a capacity of " + capacity + " and " + adjacents + "adjacent messagers");
        
    }
    
}
