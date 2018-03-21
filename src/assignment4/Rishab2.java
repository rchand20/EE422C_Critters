package assignment4;

import java.util.*;

public class Rishab2 extends Critter {
	
	private int age;
	private static int numFights;
	private static int numWalks;
	
	static {
		numFights = 0;
		numWalks = 0;
	}
	
	public Rishab2() {
		age = 0;
	}

	@Override
	public void doTimeStep() {
		if (getEnergy() > 20) {
			Rishab2 child = new Rishab2();
			Rishab2 child1 = new Rishab2();
			Rishab2 child2 = new Rishab2();
			Rishab2 child3 = new Rishab2();
			Rishab2 child4 = new Rishab2();
			Rishab2 child5 = new Rishab2();
			Rishab2 child6 = new Rishab2();
			Rishab2 child7 = new Rishab2();
			
			reproduce(child, 0);
			reproduce(child1, 1);
			reproduce(child2, 2);
			reproduce(child3, 3);
			reproduce(child4, 4);
			reproduce(child5, 5);
			reproduce(child6, 6);
			reproduce(child7, 7);
		}
	}

	@Override
	public boolean fight(String opponent) {
		numFights++;
		return true;
	}
	
	
	public static void runStats(java.util.List<Critter> r2) {
		System.out.println("Total Number of Rishab2 Critters: " + r2.size());
		System.out.println("Total Number of Walks: " + numWalks);
		System.out.println("Total Number of Fights: " + numFights);
		
		int totalAge = 0;
		for(Object c : r2) {
			Rishab2 critter = (Rishab2) c;
			totalAge += critter.age;
		}
		
		System.out.println("Average Age: " + totalAge/r2.size());
	}
	
	public String toString() {
		return "r";
	}
	
	public void test (List<Critter> l) {
		
	}
}