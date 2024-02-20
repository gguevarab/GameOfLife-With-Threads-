package com.gameoflife;

public class ConsumerCell extends Thread {

    private CellBuffer mailbox;

    private boolean ongoingLecture = true;

    public ConsumerCell(CellBuffer mailbox) {

        this.mailbox = mailbox;

    }

    public void run(){

		int[] tempBufferData = {0,0,0};
        tempBufferData = this.mailbox.leer();

		while(tempBufferData[0] + tempBufferData[1] != tempBufferData[2]){
            Thread.yield();
			tempBufferData = this.mailbox.leer();
		}

        if(tempBufferData[0] <= 3 && tempBufferData[0] > 0){
            mailbox.setAlive();
        }

    }
    
}
