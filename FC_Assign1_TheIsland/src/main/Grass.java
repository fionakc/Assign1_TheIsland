package main;

public class Grass {

	//variables
		private int Xpos;
		private int Ypos;
		private int age;
		private String symbol;
		
		public Grass(int x, int y) { //constructor
			this.Xpos=x;
			this.Ypos=y;
			this.age=(int)(Math.random()*(3)+1);
			this.symbol=String.valueOf(this.age);
		} //end constructor
		
		public void ageUp() {			
			if(this.age<9) {		
				this.age++;
				setSymbol(this.age);
			}
		}
		
		public String getSymbol() {					
			return this.symbol;
		}
		
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
		
	
} //end class
