package com.gameoflife;

public class ProductorCell extends Thread {

    private boolean isAlive;
    private int[] coordinates;
    private CellThread[][] cellMap;

    public ProductorCell(boolean isAlive, int[] coordinates, CellThread[][] cellMap) {

        this.isAlive = isAlive;
        this.coordinates = coordinates;
        this.cellMap = cellMap;

    }

    public void run() {

        for(int x = -1; x < 2; x++){
			for(int y = -1; y < 2; y++){
				if(x == 0 && y == 0){
					continue;
				}
				else if(coordinates[0] + x < 0 || coordinates[1] + y < 0){
					continue;
				} 
                else if(coordinates[0] + x < cellMap.length && coordinates[1] + y < cellMap.length){
                    cellMap[coordinates[0] + x][coordinates[1] + y].mailbox.escribir(this.isAlive);
                }
					
			}
		}

    }
    
}
