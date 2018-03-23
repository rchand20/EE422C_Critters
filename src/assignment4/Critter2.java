/**
 * Critter 2 produces a lot of children and will always fight! However critter 2 never moves so it just hopes that algae
 * will appear where it is since it is too lazy to move. Since it reproduces with relatively low energy, Critter2's often
 * fight each other and gather their brother or sister's energy. We keep track of the age of each critter 2 and then return
 * the average age. 
 */

package assignment4;

import java.util.*;

public class Critter2 extends Critter.TestCritter {
	
	private int age;
	private static int numFights;
	private static int numWalks;
	
	static {
		numFights = 0;
		numWalks = 0;
	}
	
	public Critter2() {
		age = 0;
	}
	/**
	 * This function performs one time step for Critter2
	 * The critter will reproduce with enough energy
	 */
	@Override
	public void doTimeStep() {
		if (getEnergy() > 20) {
			Critter2 child = new Critter2();
			Critter2 child1 = new Critter2();
			Critter2 child2 = new Critter2();
			Critter2 child3 = new Critter2();
			Critter2 child4 = new Critter2();
			Critter2 child5 = new Critter2();
			Critter2 child6 = new Critter2();
			Critter2 child7 = new Critter2();
			
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

	/**
	 * This function determines if a critter will fight in an encounter or not
	 * @param string of the opponent
	 * @return boolean true because this critter always fights
	 */
	@Override
	public boolean fight(String opponent) {
		numFights++;
		return true;
	}
	
	/**
	 * This function prints the stats for Critter2
	 * @param r1 a list of the Critter1's in the world
	 */
	public static void runStats(java.util.List<Critter> r2) {
		System.out.println("Total Number of Rishab2 Critters: " + r2.size());
		System.out.println("Total Number of Fights: " + numFights);
		
		int totalAge = 0;
		for(Object c : r2) {
			Critter2 critter = (Critter2) c;
			totalAge += critter.age;
		}
		
		System.out.println("Average Age: " + totalAge/r2.size());
	}
	
	/**
	 * Returns the string representation of the critter
	 * @return the string representation of the critter
	 */
	public String toString() {
		return "r";
	}
	
	public void test (List<Critter> l) {
		
	}
}