/**
 * Critter 3 always runs and will only reproduce one child if it has enough energy. Critter 3 will fight any other Critter
 * but Critter 1 regardless of it's energy. In Critter 3 stats we find the total amount of times all critter 3's have
 * moved to the left and the right as well as the total encounters and the number of children.
 */
package assignment4;

import java.util.*;

public class Critter3 extends Critter {
	
	private static int encounters;
	private static int running;
	private static int reproduced;
	private static int left;
	private static int right;

	static {
		left = 0;
		right = 0;
		running = 0;
		encounters = 0;
		reproduced = 0;
	}
	
	/**
	 * This function performs one time step for Critter3
	 * The Critter will run
	 * The critter will reproduce with enough energy
	 */
	@Override
	public void doTimeStep() {
		int dir= getRandomInt(8);
		
		if(dir == 0 || dir == 1 || dir == 7) {
			right++;
		}
		if(dir == 3 || dir == 4 || dir == 5) {
			left++;
		}
		
		run(dir);
		running++;
		
		if (getEnergy() > 50) {
			reproduced++;
			Critter3 child = new Critter3();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	/**
	 * This function determines if a critter will fight in an encounter or not
	 * @param string of the opponent
	 * @return boolean true if the critter decides to fight and false if the critter decides not to fight
	 */
	@Override
	public boolean fight(String opponent) {
		encounters++;
		if(opponent.equals("1")) return false;
		return true;
	}
	
	/**
	 * Returns the string representation of the critter
	 * @return the string representation of the critter
	 */
	public String toString() {
		return "3";
	}
	
	public void test (List<Critter> l) {
		
	}
	
	/**
	 * This function prints the stats for Critter3
	 * @param r1 a list of the Critter3's in the world
	 */
	public static void runStats(java.util.List<Critter> luckies) {
		
		//Prints number of Luckies
		System.out.print("" + luckies.size() + " total Luckies    ");
		//Prints total number of encounters for all Tara's
		System.out.print("" + encounters + " total encounters    ");
		//Prints total number of units moved
		System.out.print("" + (running*2) + " total units moved    ");
		//Prints total moves to the right by a critter 3
		System.out.print("" + (right) + " average moves right    ");
		//Prints total moves to the left by a critter 3
		System.out.print("" + (right) + " total moves left    ");
		//Prints total children reproduced
		System.out.println("" + reproduced + " total children reproduced    ");
		System.out.println();
	}
}