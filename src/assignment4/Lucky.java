package assignment4;

import java.util.*;

public class Lucky extends Critter {
	
	private static int encounters;
	private static int running;
	private static int reproduced;

	static {
		running = 0;
		encounters = 0;
		reproduced = 0;
	}
	

	@Override
	public void doTimeStep() {
		int dir= getRandomInt(8);
		run(dir);
		running++;
		
		if (getEnergy() > 50) {
			reproduced++;
			Lucky child = new Lucky();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		encounters++;
		if(opponent.equals("R")) return false;
		return true; 
		/*if(opponent.equals("@")) return true;
		if (getEnergy() > 10) return true;
		return false;
		*/
	}
	
	public String toString() {
		return "L";
	}
	
	public void test (List<Critter> l) {
		
	}
	
	public static void runStats(java.util.List<Critter> luckies) {
		
		//Prints number of Luckies
		System.out.print("" + luckies.size() + " total Luckies    ");
		//Prints total number of encounters for all Tara's
		System.out.print("" + encounters + " total encounters    ");
		//Prints total number of units moved
		System.out.print("" + (running*2) + " total units moved    ");
		//Prints total children reproduced
		System.out.println("" + reproduced + " total children reproduced    ");
		System.out.println();
	}
}