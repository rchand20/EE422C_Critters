package assignment4;
/* CRITTERS Main.java
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

import java.util.List;
import java.util.Scanner;

import com.sun.javafx.property.adapter.JavaBeanQuickAccessor;

import java.io.*;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;


/*
 * Usage: java <pkgname>.Main <input file> test
 * input file is optional.  If input file is specified, the word 'test' is optional.
 * May not use 'test' argument without specifying input file.
 */
public class Main {

	static Scanner kb;	// scanner connected to keyboard input, or input file
	private static String inputFile;	// input file, used instead of keyboard input if specified
	static ByteArrayOutputStream testOutputString;	// if test specified, holds all console output
	private static String myPackage;	// package of Critter file.  Critter cannot be in default pkg.
	private static boolean DEBUG = false; // Use it or not, as you wish!
	static PrintStream old = System.out;	// if you want to restore output to console


	// Gets the package name.  The usage assumes that Critter and its subclasses are all in the same package.
	static {
		myPackage = Critter.class.getPackage().toString().split(" ")[1];
	}

	/**
	 * Main method.
	 * @param args args can be empty.  If not empty, provide two parameters -- the first is a file name, 
	 * and the second is test (for test output, where all output to be directed to a String), or nothing.
	 */
	public static void main(String[] args) { 
		if (args.length != 0) {
			try {
				inputFile = args[0];
				kb = new Scanner(new File(inputFile));			
			} catch (FileNotFoundException e) {
				System.out.println("USAGE: java Main OR java Main <input file> <test output>");
				e.printStackTrace();
			} catch (NullPointerException e) {
				System.out.println("USAGE: java Main OR java Main <input file>  <test output>");
			}
			if (args.length >= 2) {
				if (args[1].equals("test")) { // if the word "test" is the second argument to java
					// Create a stream to hold the output
					testOutputString = new ByteArrayOutputStream();
					PrintStream ps = new PrintStream(testOutputString);
					// Save the old System.out.
					old = System.out;
					// Tell Java to use the special stream; all console output will be redirected here from now
					System.setOut(ps);
				}
			}
		} else { // if no arguments to main
			kb = new Scanner(System.in); // use keyboard and console
		}

		//while loop takes in keyboard commands till the user inputs quit
		boolean quit = true;// indicate when the user has quit the program
		while(quit) {
			System.out.print("critters>s");
			String input = kb.nextLine();
			String[] arguments = input.split(" ");


			switch(arguments[0].toLowerCase()) {

			case "quit" :
				quit = false;
				break;

			//calls display world to show the world
			case "show" :
				if(arguments.length >1) {
					System.out.println("error processing: "+ input);
				}
				else {
					Critter.displayWorld();
				}
				break;
				
			//calls worldTimeStep to take the indicated number of steps that a user inputs
			case "step" :
				int steps = 1;
				if(arguments.length>2) {
					System.out.println("error processing: "+ input);
				}
				else { 
					try {
						steps = Integer.parseInt(arguments[1]); 
					}
					catch(Exception e){
						if(e instanceof ArrayIndexOutOfBoundsException) {
							steps=1;
						}
						else {
							System.out.println("error processing: " + input);
						}
							
					}
				}
				//calls worldTimeStep the indicated number of steps that a user inputs
				for(int i = 0; i < steps; i++) {
					Critter.worldTimeStep();
				}

				break;

			//when a user inputs seed it will set the seed for the random number genrator
			case "seed" :
				if(arguments.length != 2) {
					System.out.println("error processing: "+ input);
				}
				else {
					long seed = Integer.parseInt(arguments[1]);
					Critter.setSeed(seed);
				}

				break;

			//makes the a critter when a user inputs the command make and an indicated type as well as a quantity
			case "make" :
				if(arguments.length != 3) {
					System.out.println("error processing: "+ input);
				}
				else {
					try {
						//makes the user specified number of critters
						for(int c = 0; c < Integer.parseInt(arguments[2]); c++ ) {

							Critter.makeCritter(arguments[1]);

						}
					}

					catch(Exception e){
						System.out.println("error processing: "+ input);
					}

				}
				break;

			//prints the stats for a specific critter indicated by the user
			case "stats" :
				if(arguments.length != 2) {
					System.out.println("error processing: "+ input);
				}

				else {
					try {
						
						String className = arguments[1];
						List<Critter> stats = Critter.getInstances(className);
						Class newCritter = Class.forName("assignment4." + className);
						Class[] paramList = new Class[1];
						
						paramList[0] = java.util.List.class;				
						
						Method m = newCritter.getMethod("runStats", paramList);//creates a method object for the specified run stats method
						
						m.invoke(newCritter, stats);//invokes the run stats method through the method objecy
						
					} catch (Exception e) {
						System.out.println("error processing: " + input);
					}

				}
				break;

			default:
				System.out.println("invalid command: " + input);
			}
		}




		/* Write your code above */
		//System.out.flush();

	}
}
