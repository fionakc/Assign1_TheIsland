package main;

//Fiona Crook
//300442873
//Swen501 - Assignment 1: The Island

public class Kiwi implements Animal{


	private String symbol="K";
	private  int Xpos;
	private  int Ypos;
	private  int energy;
	private  int speed;
	

	public Kiwi(int x, int y) {		
		this.Xpos = x;
		this.Ypos = y;
		this.energy = (int)((Math.random()*20)+1);		
		this.speed=1;	
	}

	//methods from interface
	public int getXpos() {
		return this.Xpos;
	}
	
	public int getYpos() {
		return this.Ypos;
	}
	
	public void setXpos(int x) {
		this.Xpos=x;
	}
	
	public void setYpos(int y) {
		this.Ypos=y;
	}
	
	public String getSymbol() {
		return this.symbol;
	}
	
	public void setSymbol(String s) {
		this.symbol=s;
	}
	
	public int getEnergy() {
		return this.energy;
	}
	
	public void setEnergy(int e) {
		this.energy=e;
	}
	
	public void move(int x, int y) {
		this.Xpos+=x;
		this.Ypos+=y;
	}
	
	public int getSpeed() {
		return this.speed;
	}
	
	public void setSpeed(int s) {
		this.speed=s;
	}	
	
	public void moveRandom() {
		int direction = (int)(Math.random()*4);		
		switch (direction) {			
			case 0: move(1,0);
					break;
			case 1:	move(0,1);
					break;
			case 2: move(-1,0);
					break;
			case 3: move(0,-1);
					break;		
		} 
	} 
	
	public void loseEnergy() {
		if(this.energy>0) {
		this.energy-=1;		
		}	
	} 
	
	public void gainEnergy() {
		this.energy++;
	}
	
	public boolean isHungry() {
		boolean hungry=false;
		if(this.energy<4) {
			hungry=true;
		}
		return hungry;
	} 

}
