package com.gameoflife;

public class Semáforo {
	
	private int contador;
	private int capacidad; 
	
	public Semáforo(int capacidad) {
		this.capacidad = capacidad;
		this.contador = capacidad;
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
		if(contador < capacidad){
			contador ++;
		}
		if(contador <= 0) {
			this.notify();
		}
	}
}




