package assignment4;

import java.util.*;

public class Lucky extends Critter.TestCritter {

	@Override
	public void doTimeStep() {
		int dir= getRandomInt(8);
		run(dir);
		
		if (getEnergy() > 50) {
			Lucky child = new Lucky();
			reproduce(child, Critter.getRandomInt(8));
		}
	}

	@Override
	public boolean fight(String opponent) {
		if(opponent.equals("assignment4.Algae")) return true;
		if (getEnergy() > 10) return true;
		return false;
	}
	
	public String toString() {
		return "L";
	}
	
	public void test (List<Critter> l) {
		
	}
}