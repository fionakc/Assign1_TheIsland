package main;

public class main {

	public static void main(String[] args) {
		
		Island firstIsland=new Island(10,20,5,5); //create world object
		firstIsland.populateIsland();
		
		Island secondIsland=new Island(10,15,3,3);
		secondIsland.populateIsland();
		
		System.out.println("First Island");
		firstIsland.drawIsland();
		
		System.out.println("Second Island");
		secondIsland.drawIsland();
		
		for(int i=1;i<=10;i++) {
			System.out.println("First Island Iteration "+i);
			firstIsland.updateIsland();
			firstIsland.drawIsland();
			System.out.println("Second Island Iteration "+i);
			secondIsland.updateIsland();
			secondIsland.drawIsland();
			
		} //end for iteration
		
		System.out.println("The number of rabbits alive on First Island is "+firstIsland.stillAlive());
		System.out.println("The number of rabbits alive on Second Island is "+secondIsland.stillAlive());
		
	} //end main
	
} //end class
