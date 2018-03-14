package assignment4;
/* CRITTERS Critter.java
 * EE422C Project 4 submission by
 * Replace <...> with your actual data.
 * <Student1 Name>
 * <Student1 EID>
 * <Student1 5-digit Unique No.>
 * <Student2 Name>
 * <Student2 EID>
 * <Student2 5-digit Unique No.>
 * Slip days used: <0>
 * Fall 2016
 */


import java.lang.reflect.Constructor;
import java.util.List;

import com.sun.org.apache.xml.internal.security.encryption.CipherReference;


/* see the PDF for descriptions of the methods and fields in this class
 * you may add fields, methods or inner classes to Critter ONLY if you make your additions private
 * no new public, protected or default-package code or data can be added to Critter
 */


public abstract class Critter {
	private static String myPackage;
	private	static List<Critter> population = new java.util.ArrayList<Critter>();
	private static List<Critter> babies = new java.util.ArrayList<Critter>();

	// Gets the package name.  This assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	private static java.util.Random rand = new java.util.Random();
	public static int getRandomInt(int max) {
		return rand.nextInt(max);
	}

	public static void setSeed(long new_seed) {
		rand = new java.util.Random(new_seed);
	}


	/* a one-character long string that visually depicts your critter in the ASCII interface */
	public String toString() { return ""; }

	private int energy = 0;
	protected int getEnergy() { return energy; }

	private int x_coord;
	private int y_coord;

	/**
	 * This function changes the position dependent on the direction it is WALKING and subtracts the amount of energy for walking 
	 * @param direction
	 */
	protected final void walk(int direction) {
		if(direction==0) {
			x_coord = moveX(1);
		}

		if(direction==1) {
			x_coord = moveX(1);
			y_coord = moveY(-1);
		}

		if(direction==2) {
			y_coord = moveY(-1);
		}

		if(direction==3) {
			x_coord = moveX(-1);
			y_coord = moveY(-1);
		}

		if(direction==4) {
			x_coord = moveX(-1);
		}

		if(direction==5) {
			x_coord = moveX(-1);
			y_coord = moveY(1);
		}

		if(direction==6) {
			y_coord = moveY(1);
		}

		if(direction==7) {
			x_coord = moveX(1);
			y_coord = moveY(1);
		}
		energy-= Params.walk_energy_cost;
	}

	protected final void run(int direction) {
		if(direction==0) {
			x_coord = moveX(2);
		}

		if(direction==1) {
			x_coord = moveX(2);
			y_coord = moveY(-2);
		}

		if(direction==2) {
			y_coord = moveY(-2);
		}

		if(direction==3) {
			x_coord = moveX(-2);
			y_coord = moveY(-2);
		}

		if(direction==4) {
			x_coord = moveX(-2);
		}

		if(direction==5) {
			x_coord = moveX(-2);
			y_coord = moveY(2);
		}

		if(direction==6) {
			y_coord = moveY(2);
		}

		if(direction==7) {
			x_coord = moveX(2);
			y_coord = moveY(2);
		}

		energy-= Params.walk_energy_cost;
	}

	protected final void reproduce(Critter offspring, int direction) {
		if(getEnergy() < Params.min_reproduce_energy) {
			return;
		}

		offspring.energy = energy/2;
		energy = energy/2 + 1;


		if(direction==0) {
			offspring.x_coord = this.moveX(1);
			offspring.y_coord = this.y_coord;
		}

		if(direction==1) {
			offspring.x_coord = this.moveX(1);
			offspring.y_coord = this.moveY(-1);
		}

		if(direction==2) {
			offspring.x_coord = this.x_coord;
			offspring.y_coord = this.moveY(-1);
		}

		if(direction==3) {
			offspring.x_coord = this.moveX(-1);
			offspring.y_coord = this.moveY(-1);
		}

		if(direction==4) {
			offspring.x_coord = this.moveX(-1);
			offspring.y_coord = this.y_coord;
		}

		if(direction==5) {
			offspring.x_coord = this.moveX(-1);
			offspring.y_coord = this.moveY(1);
		}

		if(direction==6) {
			offspring.x_coord = this.x_coord;
			offspring.y_coord = this.moveY(1);
		}

		if(direction==7) {
			offspring.x_coord = this.moveX(1);
			offspring.y_coord = this.moveY(1);
		}

		babies.add(offspring);
	}

	public abstract void doTimeStep();
	public abstract boolean fight(String oponent);

	/**
	 * create and initialize a Critter subclass.
	 * critter_class_name must be the unqualified name of a concrete subclass of Critter, if not,
	 * an InvalidCritterException must be thrown.
	 * (Java weirdness: Exception throwing does not work properly if the parameter has lower-case instead of
	 * upper. For example, if craig is supplied instead of Craig, an error is thrown instead of
	 * an Exception.)
	 * @param critter_class_name
	 * @throws InvalidCritterException
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		Class<?> newCritter= null;
		try {
			newCritter = Class.forName(critter_class_name);
			Constructor<?> constructor = null;
			constructor= newCritter.getConstructor();
			Critter c = (Critter) newCritter.newInstance();

			c.x_coord = getRandomInt(Params.world_width);
			c.y_coord = getRandomInt(Params.world_height);
			c.energy = Params.start_energy;

			population.add(c);

		} catch (ClassNotFoundException cnfe) {
			throw new InvalidCritterException(critter_class_name);
		} catch (InstantiationException iE) {
			throw new InvalidCritterException(critter_class_name);
		} catch(IllegalAccessException e) {
			throw new InvalidCritterException(critter_class_name);
		} catch (NoSuchMethodException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		Class<?> critter = null;

		try {
			critter = Class.forName(critter_class_name);
		} catch(Exception e) {

		}

		List<Critter> result = new java.util.ArrayList<Critter>();

		for(Critter c :population) {
			if(critter.isInstance(c)) {
				result.add(c);
			}
		}

		return result;
	}

	/**
	 * Prints out how many Critters of each type there are on the board.
	 * @param critters List of Critters.
	 */
	public static void runStats(List<Critter> critters) {
		System.out.print("" + critters.size() + " critters as follows -- ");
		java.util.Map<String, Integer> critter_count = new java.util.HashMap<String, Integer>();
		for (Critter crit : critters) {
			String crit_string = crit.toString();
			Integer old_count = critter_count.get(crit_string);
			if (old_count == null) {
				critter_count.put(crit_string,  1);
			} else {
				critter_count.put(crit_string, old_count.intValue() + 1);
			}
		}
		String prefix = "";
		for (String s : critter_count.keySet()) {
			System.out.print(prefix + s + ":" + critter_count.get(s));
			prefix = ", ";
		}
		System.out.println();		
	}

	/* the TestCritter class allows some critters to "cheat". If you want to 
	 * create tests of your Critter model, you can create subclasses of this class
	 * and then use the setter functions contained here. 
	 * 
	 * NOTE: you must make sure that the setter functions work with your implementation
	 * of Critter. That means, if you're recording the positions of your critters
	 * using some sort of external grid or some other data structure in addition
	 * to the x_coord and y_coord functions, then you MUST update these setter functions
	 * so that they correctly update your grid/data structure.
	 */
	static abstract class TestCritter extends Critter {
		protected void setEnergy(int new_energy_value) {
			super.energy = new_energy_value;
		}

		protected void setX_coord(int new_x_coord) {
			super.x_coord = new_x_coord;
		}

		protected void setY_coord(int new_y_coord) {
			super.y_coord = new_y_coord;
		}

		protected int getX_coord() {
			return super.x_coord;
		}

		protected int getY_coord() {
			return super.y_coord;
		}


		/*
		 * This method getPopulation has to be modified by you if you are not using the population
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.
		 */
		protected static List<Critter> getPopulation() {
			return population;
		}

		/*
		 * This method getBabies has to be modified by you if you are not using the babies
		 * ArrayList that has been provided in the starter code.  In any case, it has to be
		 * implemented for grading tests to work.  Babies should be added to the general population 
		 * at either the beginning OR the end of every timestep.
		 */
		protected static List<Critter> getBabies() {
			return babies;
		}
	}

	/**
	 * Clear the world of all critters, dead and alive
	 */
	public static void clearWorld() {
		// Complete this method.


	}
	/**
	 * This function implements doTimeStep for every critter in the collection
	 * Then it removes all dead critters from the collection
	 */
	public static void worldTimeStep() {
		// Complete this method.

		for(Critter c : population) {
			c.doTimeStep();
		}

		for(Critter a : population) {
			for(Critter b : population) {
				if(a == b) {
					continue;
				}

				if((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
					if(a.energy > 0 && b.energy > 0) {
						boolean aFight = a.fight(b.toString());
						boolean bFight = b.fight(a.toString());


						if((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
							int aRoll=0;
							if(aFight) {
								aRoll= getRandomInt(a.energy);
							}

							int bRoll=0;
							if(bFight) {
								bRoll= getRandomInt(b.energy);
							}

							if(aRoll==bRoll) {
								int tie= getRandomInt(2);
								if(tie==0) {
									//a won
									a.energy += b.energy/2;
									b.energy = 0;
								}
								else {
									//b won
									b.energy += a.energy/2;
									a.energy = 0;
								}
							}

							else if(aRoll> bRoll){
								a.energy += b.energy/2;
								b.energy = 0;
							}

							else {
								b.energy += a.energy/2;
								a.energy = 0;
							}
						}
					}
				}
			}
		}

		for(Critter c:population) {
			c.energy -= Params.rest_energy_cost;
		}

		for (int i  = 0; i < Params.refresh_algae_count; i += 1) {
			try {
				Critter.makeCritter("assignment4.Algae");
			} catch (InvalidCritterException e) {

			}
		}

		for(Critter c : babies) {
			population.add(c);
		}

		java.util.ArrayList<Critter> temp = new java.util.ArrayList<Critter>(babies);
		babies.removeAll(temp);


		java.util.ArrayList<Critter> toRemove = new java.util.ArrayList<Critter>();
		for(Critter c: population) {
			if(c.energy <= 0) {
				toRemove.add(c);
			}
		}

		for(Critter c: toRemove) {
			population.remove(c);
		}

	}

	/**
	 * This method creates a two dimensional array to store the grid
	 * It places all the algae and critters in the array
	 * Then it prints the array
	 */
	public static void displayWorld() {
		// Complete this method.

		String[][] world = new String[82][162];

		for(int y=0; y < 82; y++) {
			for(int x=0; x<162; x++) {
				world[y][x]=" ";
			}
		}

		world[0][0] = "+";
		world[0][161] = "+";
		world[81][161] = "+";
		world[81][0] = "+";


		for(int x = 1; x < 161; x++) {
			world[0][x]= "-";
			world[81][x]= "-";
		}

		for(int y= 1; y < 81; y++) {
			world[y][0]= "|";
			world[y][161]= "|";
		}

		for(Critter c: population) {
			world[c.y_coord + 1][c.x_coord + 1] = c.toString();
		}

		for(int y=0; y < 82; y++) {
			for(int x=0; x<162; x++) {
				System.out.print(world[y][x]);
			}
			System.out.println();
		}
	}

	private int moveX(int steps) {
		if(x_coord == 0) {
			if(steps<0) {
				return (Params.world_width + steps);
			}
			else {
				return (x_coord + steps);
			}	
		}
		
		if(x_coord==1) {
			if(steps == -2) {
				return Params.world_width - 1;
			}
			else {
				return x_coord + steps;
			}
		}
		
		if(x_coord == 159) {
			if(steps>0) {
				return steps - 1;
			}
			else {
				return x_coord + steps;
			}
		}
		
		if(x_coord == 158) {
			if(steps == 2) {
				return 1;
			}
			else {
				return x_coord + steps;
			}
		}
		
		return x_coord + steps;
	}
	
	private int moveY(int steps) {
		if(y_coord == 0) {
			if(steps<0) {
				return (Params.world_height + steps);
			}
			else {
				return (y_coord + steps);
			}	
		}
		
		if(y_coord==1) {
			if(steps == -2) {
				return Params.world_height - 1;
			}
			else {
				return y_coord + steps;
			}
		}
		
		if(y_coord == 79) {
			if(steps>0) {
				return steps - 1;
			}
			else {
				return y_coord + steps;
			}
		}
		
		if(y_coord == 78) {
			if(steps == 2) {
				return 1;
			}
			else {
				return y_coord + steps;
			}
		}
		
		return y_coord + steps;
	}
}
