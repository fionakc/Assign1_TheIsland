package main;

//Fiona Crook
//300442873
//Swen501 - Assignment 1: The Island

public class Grass {

	
		private int Xpos;
		private int Ypos;
		private int age; //used as analogy for energy contained also
		private String symbol;
		
		public Grass(int x, int y) { 
			this.Xpos=x;
			this.Ypos=y;
			this.age=(int)(Math.random()*(3)+1);
			this.symbol=String.valueOf(this.age);
		} 
		
		//increases plant age up to value of 9
		public void ageUp() {			
			if(this.age<9) {		
				this.age++;
				setSymbol(this.age);
			}
		}
		
		public String getSymbol() {					
			return this.symbol;
		}
		
		//uses plant age as plant symbol
		public void setSymbol(int age) {
			this.symbol=String.valueOf(age);
		}
				
		public int getXpos() {
			return Xpos;
		}
		public void setXpos(int x) {
			this.Xpos=x;
		}
		
		public int getYpos() {
			return Ypos;
		}
		public void setYpos(int y) {
			this.Ypos=y;
		}
		
		public int getAge() {
			return this.age;
		}
		
		public void eaten() {
			//can only eat plant if it still has age/energy
			if(this.age>0) {
				this.age--;
			}			
			setSymbol(this.age);
		}
			
} 
