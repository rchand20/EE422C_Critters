package assignment4;

import java.util.*;

public class Tara1 extends Critter {
	
	private boolean hasMoved;
	private static int encounters;
	private static int walking;
	private static int running;
	private static int staying;

	public Tara1() {
		hasMoved = false;
	}
	
	static {
		staying = 0;
		running = 0;
		walking = 0;
		encounters = 0;
	}
	
	@Override
	public void doTimeStep() {
		int dir = getRandomInt(8);
		int move = getRandomInt(3);
		
		if(move == 0) {
			walking++;
			hasMoved = true;
			walk(dir);
		}
		else if(move == 1) {
			running++;
			hasMoved = true;
			run(dir);
		}
		else {
			staying++;
		}
		
		if (getEnergy() > 10) {
			Tara1 child = new Tara1();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		encounters++;
		if(hasMoved) return true;
		if(opponent.equals("@")) return true;
		if(opponent.equals("R")) return true;
		if (getEnergy() > 50) return true;
		return false;
	}
	
	public String toString() {
		return "T";
	}
	
	public void test (List<Critter> l) {
		
	}
	
	public static void runStats(java.util.List<Critter> taras) {
		int total_moves = walking + running + staying;
		
		//Prints number of Tara's
		System.out.print("" + taras.size() + " total Taras    ");
		//Prints total number of encounters for all Tara's
		System.out.print("" + encounters + " total encounters    ");
		//Prints percent of moves walking, running or staying
		System.out.println(""+ (walking/total_moves)*100 + "% of moves walking    ");
		System.out.println(""+ (running/total_moves)*100 + "% of moves running    ");
		System.out.println(""+ (staying/total_moves)*100 + "% of moves staying");
		System.out.println();
	}
}