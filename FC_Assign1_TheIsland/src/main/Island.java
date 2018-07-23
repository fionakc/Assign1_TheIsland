package main;

import java.util.ArrayList;

public class Island {
	
	//variables - make these changeable
	private int worldHeighty;
	private int worldWidthx;
	private String [][] field;
	private int numberOfRabbits;
	private int numberOfKiwi;
	private int numberRabbitsAlive;
	private ArrayList<Animal> animalList=new ArrayList<Animal>();
	private ArrayList<Grass> grassList=new ArrayList<Grass>();
	private final int numberOfGrass = 5;
	
	//constructor
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
				} //end x loop			
			} //end y loop			
			
		} //end constructor
		
		//===========================================
	public void populateIsland() {
			
		boolean grassAdded=false;
		
		for(int i=0;i<this.numberOfGrass;i++) {
			grassAdded=false;
			while(!grassAdded) {
				int Xpos = (int)(Math.random()*(this.worldWidthx)); //create new plant position values
				int Ypos = (int)(Math.random()*(this.worldHeighty));
				boolean checkCollide=detectCollideAll(Xpos,Ypos);
				if(!checkCollide) {
					Grass grassTemp = new Grass (Xpos,Ypos);
					this.grassList.add(grassTemp);
					grassAdded=true;
				} //end if notcheckCollide
				//if collides, will circle back up and generate new xpos, ypos
				
			} //end while not animalDone
			
		} //end for grassList
		
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
					} //end if notcheckCollide
					//if collides, will circle back up and generate new xpos, ypos					
				} //end while not rabbitAdded				
			} //end for numberOfRabbits
			
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
					} //end if notcheckCollide
					//if collides, will circle back up and generate new xpos, ypos					
				} //end while not kiwiAdded				
			} //end for numberOfKiwi
		
			
	} //end populate
	
	
		//======================================
	public void drawIsland() {
			
			//empty out array
			for(int y=0;y<this.worldHeighty;y++) {
				for(int x=0;x<this.worldWidthx;x++) {
					this.field[y][x]=".";				
				} //end x loop			
			} //end y loop	
			
			//add in grass to array - assume no collisions
			
			for (int i=0;i<this.grassList.size();i++) {	
				
				int xValue=this.grassList.get(i).getXpos(); //extract values
				
				int yValue=this.grassList.get(i).getYpos();
				
				String symbol = this.grassList.get(i).getSymbol();
				
				this.field[yValue][xValue]=symbol;
							
			} //end draw grass for loop
			
			
			//add in animals to array - assume no collisions			
			for (int i=0;i<this.animalList.size();i++) {					
				int xValue=this.animalList.get(i).getXpos(); //extract values				
				int yValue=this.animalList.get(i).getYpos();				
				String symbol = this.animalList.get(i).getSymbol();				
				this.field[yValue][xValue]=symbol;							
			} //end draw animal for loop
			
			//draw out array
			for(int y=0;y<this.worldHeighty;y++) {
				for(int x=0;x<this.worldWidthx;x++) {
					System.out.print(this.field[y][x]);
					
				} //end x loop
				System.out.println();
			} //end y loop		
				
			
		} //end drawIsland
	
		//===========================================
	public void updateIsland() {
		
		for(int i=0;i<this.grassList.size();i++) {
			this.grassList.get(i).ageUp();			
		} //end age grass
		
		this.numberRabbitsAlive=0;
		for(int j=0;j<this.animalList.size();j++) {
			
			int tryTimes=0;
			
			if(this.animalList.get(j).getEnergy()>0) {
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
				} //end if doesCollide
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
			} //end while doesCollide			
			//it now shouldn't collide with anything			
			this.animalList.get(j).setXpos(xTempStop);
			this.animalList.get(j).setYpos(yTempStop);
			this.animalList.get(j).loseEnergy();			
			if(this.animalList.get(j).getSymbol().equals("R")) {
				this.numberRabbitsAlive++;		
			}
			} //end if animal has energy
			
			//if animal has not energy, mark as dead
			else {
				this.animalList.get(j).setSymbol("X");
			} //end else
			
		} //end animal move for loop
		
		//kiwi eat wherever they are
		for(int k=0;k<this.animalList.size();k++) {
			String sym=this.animalList.get(k).getSymbol();
			if(sym.equals("K")) {
				this.animalList.get(k).gainEnergy();
			} //end if kiwi object
		} //end for animalList
		
		

	} //end updateIsland
		
		//===========================================
		private boolean detectCollideAll(int x, int y) {
			int xVal = x;
			int yVal = y;

			boolean	collides=false;
				
			//check stays within boundaries
			//check if collides with walls
			if(!collides) {
			
				//collides with sides
				if(xVal<0 || xVal>=this.worldWidthx) {
					collides=true;
					//break;
				}
				//collides with top and bottom
				if(yVal<0 || yVal>=this.worldHeighty) {
					collides=true;
					//break;
				}	
				
			} //end if !collides walls
			
				//check if collides with grass
				if(!collides) {					
					for (int i=0;i<this.grassList.size();i++) {						
						if(xVal==this.grassList.get(i).getXpos() && yVal==this.grassList.get(i).getYpos()) {
							collides=true;
							break;
						} //end if check
					} //end for grassList					
				} //end if !collides grass
				
				//check if collides with animals
				if(!collides) {					
					for (int i=0;i<this.animalList.size();i++) {												
						if(xVal==this.animalList.get(i).getXpos() && yVal==this.animalList.get(i).getYpos()) {					
							collides=true;				
							break;
						} //end if check						
					} //end for animalList				
				} //end if !collides animals				

		return collides;
			
		} //end detectCollideAll
		
		//===========================================
		public int stillAlive() {
		//	int alive=0;
		//	for(int a=0;a<this.animalList.size();a++) {
		//		if(this.animalList.get(a).getEnergy() >0) {
		//			alive++;
		//		} //end if
		//	} //end for loop
			
		//	return alive;
			return this.numberRabbitsAlive;
		} //end stillAlive
		
		//===========================================
		public boolean checkAdjacent(int x, int y) {
			int xVal = x;
			int yVal = y;			
			boolean adjacent=false;
			
			for(int i=0;i<this.grassList.size();i++) {
				int grassX=this.grassList.get(i).getXpos();
				int grassY=this.grassList.get(i).getYpos();
				if((yVal==grassY && Math.abs(xVal-grassX)==1) || (xVal==grassX && Math.abs(yVal-grassY)==1)) {
					adjacent=true;
				} //end if
			} //end for grassList
			
			return adjacent;
		} //end checkAdjacent
		
		//===========================================
		
} //end class
