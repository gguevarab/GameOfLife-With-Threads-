package com.gameoflife;

public class ConsumerCell extends Thread {

    private int nAlive;
    private CellBuffer mailbox;

    private boolean ongoingLecture = true;

    public ConsumerCell(CellBuffer mailbox) {

        this.mailbox = mailbox;

    }

    public void run(){

        int nAlive = 0;
		int[] tempBufferData = {0,0,0};

		while(this.ongoingLecture){

			tempBufferData = this.mailbox.leer();

			if(tempBufferData[0] + tempBufferData[1] == tempBufferData[2]){
				this.ongoingLecture = false;
			}
		
		}

        if(tempBufferData[0] <= 3 && tempBufferData[0] > 0){
            mailbox.setAlive();
        }

    }
    
}
