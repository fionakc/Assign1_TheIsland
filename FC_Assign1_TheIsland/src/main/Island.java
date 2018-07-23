package main;

//Fiona Crook
//300442873
//Swen501 - Assignment 1: The Island

import java.util.ArrayList;

public class Island {
	
	
	private int worldHeighty;
	private int worldWidthx;
	private String [][] field;
	private int numberOfRabbits;
	private int numberOfKiwi;
	private int numberRabbitsAlive;
	private ArrayList<Animal> animalList=new ArrayList<Animal>();
	private ArrayList<Grass> grassList=new ArrayList<Grass>();
	private final int numberOfGrass = 10;
	private int grassAdjX;
	private int grassAdjY;
	private int grassDirX;
	private int grassDirY;
	
	
	public Island(int heightY, int widthX, int rabbitNum, int kiwiNum) {
		
		this.worldHeighty=heightY;
		this.worldWidthx=widthX;	
		this.numberOfRabbits=rabbitNum;
		this.numberOfKiwi=kiwiNum;
		field = new String [this.worldHeighty][this.worldWidthx];
		
		//initialising the field array to empty spaces
		for(int y=0;y<this.worldHeighty;y++) {
			for(int x=0;x<this.worldWidthx;x++) {
				this.field[y][x]=".";
			} 			
		} 			
			
	} 
		
		
	public void populateIsland() {
			
		//adding grass to the island
		boolean grassAdded=false;		
		for(int i=0;i<this.numberOfGrass;i++) {
			grassAdded=false;
			while(!grassAdded) {
				int Xpos = (int)(Math.random()*(this.worldWidthx)); //create new grass position values
				int Ypos = (int)(Math.random()*(this.worldHeighty));
				boolean checkCollide=detectCollideAll(Xpos,Ypos);
				if(!checkCollide) {
					Grass grassTemp = new Grass (Xpos,Ypos);
					this.grassList.add(grassTemp);
					grassAdded=true;
				} 
			}			
		}
		
		//adding rabbits to the island
		boolean rabbitAdded=false;		
		for(int i=0;i<this.numberOfRabbits;i++) {
			rabbitAdded=false;
			while(!rabbitAdded) {
				int Xpos = (int)(Math.random()*(this.worldWidthx)); //create new animal position values
				int Ypos = (int)(Math.random()*(this.worldHeighty));
				boolean checkCollide=detectCollideAll(Xpos,Ypos);
				if(!checkCollide) {
					Rabbit rabbitTemp = new Rabbit (Xpos,Ypos);
					this.animalList.add(rabbitTemp);
					rabbitAdded=true;
				} 				
			} 				
		} 
		
		//adding kiwi to the island
		boolean kiwiAdded=false;		
		for(int i=0;i<this.numberOfKiwi;i++) {
			kiwiAdded=false;
			while(!kiwiAdded) {
				int Xpos = (int)(Math.random()*(this.worldWidthx)); //create new animal position values
				int Ypos = (int)(Math.random()*(this.worldHeighty));
				boolean checkCollide=detectCollideAll(Xpos,Ypos);
				if(!checkCollide) {
					Kiwi kiwiTemp = new Kiwi (Xpos,Ypos);
					this.animalList.add(kiwiTemp);
					kiwiAdded=true;
				} 					
			} 				
		} 
					
	} 
	
	

	public void drawIsland() {
			
		//empty out array
		for(int y=0;y<this.worldHeighty;y++) {
			for(int x=0;x<this.worldWidthx;x++) {
				this.field[y][x]=".";				
			} 			
		} 	
		
		
		//add in grass to island array - assume no collisions		
		for (int i=0;i<this.grassList.size();i++) {					
			int xValue=this.grassList.get(i).getXpos(); 				
			int yValue=this.grassList.get(i).getYpos();				
			String symbol = this.grassList.get(i).getSymbol();				
			this.field[yValue][xValue]=symbol;						
		} 
		
		
		//add in animals to island array - assume no collisions			
		for (int i=0;i<this.animalList.size();i++) {					
			int xValue=this.animalList.get(i).getXpos(); 				
			int yValue=this.animalList.get(i).getYpos();				
			String symbol = this.animalList.get(i).getSymbol();				
			this.field[yValue][xValue]=symbol;							
		} 
		
		//draw out island array to screen
		for(int y=0;y<this.worldHeighty;y++) {
			for(int x=0;x<this.worldWidthx;x++) {
				System.out.print(this.field[y][x]);				
			} 
			System.out.println();
		} 	
			
		
	} 
	
		
	public void updateIsland() {
		
		//grass grows
		for(int i=0;i<this.grassList.size();i++) {
			this.grassList.get(i).ageUp();			
		} 
		
		//animals move
		this.numberRabbitsAlive=0;
		for(int j=0;j<this.animalList.size();j++) {			
			int tryTimes=0;	
			//if animal is still alive (has positive energy)
			if(this.animalList.get(j).getEnergy()>0) {
				
				//searching for food is not currently set up, but here is where it would be
				//if animal is not hungry, randomly search for food
				//if animal is hungry, check which direction food is in and move that way
				
				//boolean hungry=this.animalList.get(j).isHungry();
				//if not hungry, move randomly
				//if(!hungry) {
					int xTempStart=this.animalList.get(j).getXpos();
					int yTempStart=this.animalList.get(j).getYpos();
					int xTempStop=xTempStart;
					int yTempStop=yTempStart;
					Rabbit rabbitTemp=new Rabbit(xTempStart,yTempStart);				
					boolean doesCollide=true;
				
					while(doesCollide) {	
						tryTimes++;
						doesCollide=false;				
						rabbitTemp.moveRandom(); //apply movement
						doesCollide=detectCollideAll(rabbitTemp.getXpos(),rabbitTemp.getYpos()); //find if collides
					
						if(doesCollide) { //if does, reset rabbitTemp to rabbitList values					
							rabbitTemp.setXpos(xTempStart);
							rabbitTemp.setYpos(yTempStart);				
						} 
						else { //if !doesCollide, will exit the while loop
							xTempStop=rabbitTemp.getXpos();
							yTempStop=rabbitTemp.getYpos();
						}
						
						//if keeps trying to move and fails, don't move
						if(tryTimes>8) {
							xTempStop=xTempStart;
							yTempStop=yTempStart;
							break;
						}
					} 			
					//it now shouldn't collide with anything			
					this.animalList.get(j).setXpos(xTempStop);
					this.animalList.get(j).setYpos(yTempStop);
					this.animalList.get(j).loseEnergy();			
					if(this.animalList.get(j).getSymbol().equals("R")) {
						this.numberRabbitsAlive++;		
					} 
				//} //end if animal not hungry
				
				//what to do if animal is hungry
				//else {
					
					
					
				//}
				
			} 
				
				//if animal has 0 energy, mark as dead
			else {
				this.animalList.get(j).setSymbol("X");
			} 
				
		} 
		
		
		//check if animals eat anything
		for(int k=0;k<this.animalList.size();k++) {
			String sym=this.animalList.get(k).getSymbol();
			//kiwi eat wherever they are
			if(sym.equals("K")) {
				this.animalList.get(k).gainEnergy();

			} 
			//rabbits eat if adjacent to plants
			else if(sym.equals("R")) {
				boolean nextToPlant=checkAdjacent(this.animalList.get(k).getXpos(),this.animalList.get(k).getYpos());
				if(nextToPlant) {
					this.animalList.get(k).gainEnergy();
					for(int m=0;m<this.grassList.size();m++) {
						if(this.grassList.get(m).getXpos()==grassAdjX && this.grassList.get(m).getYpos()==grassAdjY) {
							this.grassList.get(m).eaten();
							break;
						} 
					} 
				} 
			} 
			
		} 
		
		

	} 
		
		
	private boolean detectCollideAll(int x, int y) {
		int xVal = x;
		int yVal = y;

		boolean	collides=false;
			
		//check stays within boundaries
		if(!collides) {
		
			//collides with sides
			if(xVal<0 || xVal>=this.worldWidthx) {
				collides=true;
			}
			//collides with top and bottom
			if(yVal<0 || yVal>=this.worldHeighty) {
				collides=true;
			}	
			
		} 
		
		//check if collides with grass
		if(!collides) {					
			for (int i=0;i<this.grassList.size();i++) {						
				if(xVal==this.grassList.get(i).getXpos() && yVal==this.grassList.get(i).getYpos()) {
					collides=true;
					break;
				} 
			} 				
		} 
		
		//check if collides with animals
		if(!collides) {					
			for (int i=0;i<this.animalList.size();i++) {												
				if(xVal==this.animalList.get(i).getXpos() && yVal==this.animalList.get(i).getYpos()) {					
					collides=true;				
					break;
				} 						
			} 			
		} 				

	return collides;
		
	} 
		
		
	public int stillAlive() {	
		return this.numberRabbitsAlive;
	} 
		
		
	public boolean checkAdjacent(int x, int y) {
		int xVal = x;
		int yVal = y;			
		boolean adjacent=false;
		
		//check if object is next to a grass object
		for(int i=0;i<this.grassList.size();i++) {
			int grassX=this.grassList.get(i).getXpos();
			int grassY=this.grassList.get(i).getYpos();
			if((yVal==grassY && Math.abs(xVal-grassX)==1) || (xVal==grassX && Math.abs(yVal-grassY)==1)) {
				adjacent=true;
				this.grassAdjX=grassX;
				this.grassAdjY=grassY;
				break;
			} 
		} 

		return adjacent;
	} 
		
	//this method is not finalised, and so is not used yet
	//it can search for food
	//but I haven't yet got the rabbit movement direction towards food working
	public boolean checkForFood(int x, int y) {
		int xVal=x;
		int yVal=y;
		int grassX=0;
		int grassY=0;
		this.grassDirX=0;
		this.grassDirY=0;
		boolean food=false;
		for(int i=yVal-2;i<=yVal+2;i++) {
			for(int j=xVal-2;j<=xVal+2;j++) {
				
				for(int k=0;k<grassList.size();k++) {
					grassX=this.grassList.get(i).getXpos();
					grassY=this.grassList.get(i).getYpos();
					
					if(xVal==grassX && yVal==grassY) {
						food=true;
						break;
					} 
					
				} 
				if(food) {
					break;
				}
			} 
			
			if(food) {
				break;
			}
		} 	
		
		//setting search direction
		
		if(xVal-grassX>0) {
			this.grassDirX=-1;
		}
		else if(xVal-grassX<0) {
			this.grassDirX=1;
		}
		else if(yVal-grassY>0) {
			this.grassDirY=-1;
		}
		else if(yVal-grassY<0) {
			this.grassDirY=1;
		}
		
		return food;
	} 
		
		
		
} 
