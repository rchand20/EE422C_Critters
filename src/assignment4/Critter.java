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
/**
 * This function changes the position of a critter that is RUNNING and subtracts the running energy
 * @param direction
 */
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

		energy-= Params.run_energy_cost;
	}

	/**
	 * This designates the position of a new offspring based on the direction given.
	 * This method adds the offspring to the babies list.
	 * @param offspring
	 * @param direction
	 */
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
	 * @param critter_class_name is the name of the critter class that the user wants to create
	 * @throws InvalidCritterException will be thrown if the class the user wants to create does not exist
	 */
	public static void makeCritter(String critter_class_name) throws InvalidCritterException {
		String critterName = "assignment4." + critter_class_name;
		Class<?> newCritter= null;
		try {
			newCritter = Class.forName(critterName);
			Constructor<?> constructor = null;
			constructor= newCritter.getConstructor();
			Critter c = (Critter) newCritter.newInstance();//creates a new object of a specified constructor

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
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Gets a list of critters of a specific type.
	 * @param critter_class_name What kind of Critter is to be listed.  Unqualified class name.
	 * @return List of Critters.
	 * @throws InvalidCritterException if the class that we are trying to get an instance of does not exist
	 */
	public static List<Critter> getInstances(String critter_class_name) throws InvalidCritterException {
		Class<?> critter = null;
		String critterName = "assignment4." + critter_class_name;

		try {
			critter = Class.forName(critterName);
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
		population.clear();
		babies.clear();
		return;

	}
	/**
	 * This function implements doTimeStep for every critter in the collection
	 * Then performs any encounters if to critters are in the same position
	 * Then deducts all critters rest energy
	 * Then adds more algae to the world
	 * Then adds the babies to the world
	 */
	public static void worldTimeStep() {

		for(Critter c : population) {
			c.doTimeStep();
		}

		for(Critter a : population) {
			for(Critter b : population) {
				if(a == b) {
					continue;
				}
				//checks if two critters are in the same position and must have an encounter
				if((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
					//checks if both critters are alive for the encounter
					if(a.energy > 0 && b.energy > 0) {
						boolean aFight = a.fight(b.toString());//true if a chooses to fight
						boolean bFight = b.fight(a.toString());//true if b chooses to fight

						//proceeds with the fight if critter a or be did not choose to run away instead of fight
						if((a.x_coord == b.x_coord) && (a.y_coord == b.y_coord)) {
							int aRoll=0;
							if(aFight) {
								aRoll= getRandomInt(a.energy);//A's roll in a fight
							}

							int bRoll=0;
							if(bFight) {
								bRoll= getRandomInt(b.energy);//B's roll in a fight
							}
							
							//if they are tied uses the random nnumber generator to determine who will actually win
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
							
							//a wins
							else if(aRoll> bRoll){
								a.energy += b.energy/2;
								b.energy = 0;
							}

							//b wins
							else {
								b.energy += a.energy/2;
								a.energy = 0;
							}
						}
					}
				}
			}
		}

		//deducts rest energy cost for all critters
		for(Critter c:population) {
			c.energy -= Params.rest_energy_cost;
		}

		//produces more algae
		for (int i  = 0; i < Params.refresh_algae_count; i += 1) {
			try {
				Critter.makeCritter("assignment4.Algae");
			} catch (InvalidCritterException e) {

			}
		}

		//adds the babies to the population
		for(Critter c : babies) {
			population.add(c);
		}

		java.util.ArrayList<Critter> temp = new java.util.ArrayList<Critter>(babies);
		babies.removeAll(temp);//clears all babies from the babies list

		//removes all dead critters
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
		
		int height = Params.world_height;
		int width = Params.world_width;
		
		String[][] world = new String[height + 2][width + 2];

		for(int y=0; y < height+2; y++) {
			for(int x=0; x<width+2; x++) {
				world[y][x]=" ";
			}
		}

		world[0][0] = "+";
		world[0][width + 1] = "+";
		world[height + 1][width + 1] = "+";
		world[height + 1][0] = "+";


		for(int x = 1; x < width + 1; x++) {
			world[0][x]= "-";
			world[height + 1][x]= "-";
		}

		for(int y= 1; y < height + 1; y++) {
			world[y][0]= "|";
			world[y][width + 1]= "|";
		}

		for(Critter c: population) {
			world[c.y_coord + 1][c.x_coord + 1] = c.toString();
		}

		for(int y=0; y < height + 2; y++) {
			for(int x=0; x<width + 2; x++) {
				System.out.print(world[y][x]);
			}
			System.out.println();
		}
	}

	/**
	 * This function determines the x position after moving either right or left
	 * @param steps where -1 is walking left, -2 is running left, 1 is walking right and 2 is running right
	 * @return the X position after moving
	 */
	private int moveX(int steps) {
		int width = Params.world_width;
		
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
		
		if(x_coord == width - 1) {
			if(steps>0) {
				return steps - 1;
			}
			else {
				return x_coord + steps;
			}
		}
		
		if(x_coord == width - 2) {
			if(steps == 2) {
				return 1;
			}
			else {
				return x_coord + steps;
			}
		}
		
		return x_coord + steps;
	}
	
	/**
	 * This function determines the y position after moving either up or down
	 * @param steps where -1 is walking down, -2 is running down, 1 is walking up and 2 is running up
	 * @return the Y position after moving
	 */
	private int moveY(int steps) {
		
		int height = Params.world_height;
		
		
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
		
		if(y_coord == height - 1) {
			if(steps>0) {
				return steps - 1;
			}
			else {
				return y_coord + steps;
			}
		}
		
		if(y_coord == height - 2) {
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
