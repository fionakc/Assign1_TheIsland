package main;

//Fiona Crook
//300442873
//Swen501 - Assignment 1: The Island


public class main {

	public static void main(String[] args) {
		
		Island firstIsland=new Island(10,20,10,5);
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
			
		} 
		
		System.out.println("The number of rabbits alive on First Island is "+firstIsland.stillAlive());
		System.out.println("The number of rabbits alive on Second Island is "+secondIsland.stillAlive());
		
	} 
	
} 
