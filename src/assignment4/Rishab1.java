package assignment4;

import java.util.*;

public class Rishab1 extends Critter.TestCritter {
	
	private boolean ran;
	
	public Rishab1() {
		ran = false;
	}

	@Override
	public void doTimeStep() {
		int dir= getRandomInt(8);
		int walkOrRun = getRandomInt(2);
		
		if(walkOrRun == 0) {
			walk(dir);
		}
		
		if(walkOrRun == 1) {
			run(dir);
			ran = true;
		}
		
		if (getEnergy() > 77) {
			Rishab1 child = new Rishab1();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
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
	
	public void test (List<Critter> l) {
		
	}
}