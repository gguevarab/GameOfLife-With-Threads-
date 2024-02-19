package com.gameoflife;

public class Semáforo {
	
	private int contador;
	
	public Semáforo(int pContador) {
		this.contador = pContador;
	}
	
	public synchronized void p() {
		contador --;
		if(contador < 0) {
			try {
				this.wait();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized void v() {
		contador ++;
		if(contador <= 0) {
			this.notify();
		}
	}
}




