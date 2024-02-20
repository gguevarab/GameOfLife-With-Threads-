package com.gameoflife;

//Buffer class
public class CellBuffer {

    private boolean success = false;
    private int nAlive = 0;
    private int nDead = 0;
    private int nAdjacents;
    private int capacity;
    private boolean cellIsAlive;
    private Semáforo semaforo;

    public void escribir(boolean estado){
        semaforo.p();
    	if(estado) 
    	{
        	this.nAlive++;
    	}
    	else 
    	{
        	this.nDead++;
    	}
    }
    
    public int[] leer() 
    {
        semaforo.v();
    	return new int[] {this.nAlive, this.nDead, nAdjacents};
    }

    public void setAlive(){

        if (cellIsAlive) {
            this.success = true;
        } else {
            if (nAlive == 3) {
                this.success = true;
            }
        }
        

    }

    public boolean getAliveCell(){

        return success;

    }

    public int getNAdjacents(){

        return this.nAdjacents;

    }

    public int getCapacity(){

        return this.capacity;

    }


    public CellBuffer(int capacity, int adjacents, boolean cellStatus){

        this.nAdjacents = adjacents;
        this.capacity = capacity;
        this.semaforo  = new Semáforo(capacity);
        this.cellIsAlive = cellStatus;
        //System.out.println("I'm a mailbox with a capacity of " + capacity + " and " + adjacents + "adjacent messagers");
        
    }
    
}
