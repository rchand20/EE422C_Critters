package assignment4;

import java.util.*;

public class Rishab2 extends Critter.TestCritter {
	
	private boolean hasMoved;
	private static int numFights;
	private static int numWalks;
	
	static {
		numFights = 0;
		numWalks = 0;
	}
	
	public Rishab2() {
		hasMoved = false;
	}

	@Override
	public void doTimeStep() {
		int dir= getRandomInt(8);
		int walkOrDont = getRandomInt(2);
		
		if(walkOrDont == 0) {
			walk(dir);
			numWalks++;
			hasMoved = true;
		} 
		
		
		if (getEnergy() > 77) {
			Rishab1 child = new Rishab1();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		numFights++;
		int dir= getRandomInt(8);
		
		if(opponent.equals("@")) return true;
		
		if(!hasMoved) { 
			run(dir);
			return false;
		}
		
		else {
			return true;
		}
		
		
	}
	
	
	public static void runStats(java.util.List<Critter> r2) {
		System.out.println("Total Number of Rishab2 Critters: " + r2.size());
		System.out.println("Total Number of Walks: " + numWalks);
		System.out.println("Total Number of Fights: " + numFights);
	}
	
	public String toString() {
		return "r";
	}
	
	public void test (List<Critter> l) {
		
	}
}