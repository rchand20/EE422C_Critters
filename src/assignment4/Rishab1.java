package assignment4;

import java.util.*;

public class Rishab1 extends Critter.TestCritter {
	
	private boolean ran;
	private static int numRuns;
	private static int numWalks;
	private static int numFights;
	
	public Rishab1() {
		ran = false;
	}
	
	static {
		numRuns = 0;
		numWalks = 0;
		numFights = 0;
	}

	@Override
	public void doTimeStep() {
		int dir= getRandomInt(8);
		int walkOrRun = getRandomInt(2);
		
		if(walkOrRun == 0) {
			walk(dir);
			numWalks++;
		}
		
		if(walkOrRun == 1) {
			run(dir);
			numRuns++;
			ran = true;
		}
		
		if (getEnergy() > 77) {
			Rishab1 child = new Rishab1();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		numFights++;
		if(opponent.equals("assignment4.Algae")) return true;
		if(ran) {
			return false;
		}
		else {
			return true;
		}
	}
	
	public String toString() {
		return "R";
	}
	
	
	public static void runStats(java.util.List<Critter> r1) {
		System.out.println("Total Number of Rishab1 Critters: " + r1.size());
		System.out.println("Total Number of Runs: " + numRuns);
		System.out.println("Total Number of Walks: " + numWalks);
		System.out.println("Total Number of Fights: " + numFights);
	}
	
	public void test (List<Critter> l) {
		
	}
}