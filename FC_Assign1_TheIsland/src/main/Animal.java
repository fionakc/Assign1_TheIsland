package main;

public interface Animal {

	public int getXpos();
	public int getYpos();
	public void setXpos(int x);
	public void setYpos(int y);
	public String getSymbol();
	public void setSymbol(String s);
	public int getEnergy();
	public void setEnergy(int e);
	public void move(int x, int y);
	public int getSpeed();
	public void setSpeed(int s);
	public void moveRandom();
	public void loseEnergy();
	public void gainEnergy();
	public boolean isHungry();
	
	
} //end interface
