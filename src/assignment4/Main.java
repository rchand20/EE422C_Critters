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

		/* Do not alter the code above for your submission. */
		/* Write your code below. */
		/*
        for(int i = 0;i<100;i++) {
        	try {
				Critter.makeCritter("assignment4.Algae");
			} catch (InvalidCritterException e) {

			}
        }

        for(int i = 0; i < 25; i++) {
        	try {
				Critter.makeCritter("assignment4.Craig");
			} catch (InvalidCritterException e) {

			}
        }

        for(int i = 0; i < 15; i++) {
        	try {
				Critter.makeCritter("assignment4.Lucky");
			} catch (InvalidCritterException e) {

			}
        }

        for(int i = 0; i < 20; i++) {
        	try {
        		Critter.makeCritter("assignment4.Rishab1");
        	} catch (InvalidCritterException e) {

        	}
        }
		 */

		// System.out.println("GLHF");

		while(true) {
			System.out.println("critters>");
			String input = kb.nextLine();
			String[] arguments = input.split(" ");


			switch(arguments[0].toLowerCase()) {

			case "quit" :
				System.exit(0);
				break;

			case "show" :
				if(arguments.length >1) {
					System.out.println("invalid command: " + input);
				}
				else {
					Critter.displayWorld();
					try {
						System.out.println(Critter.getInstances("assignment4.Algae").size());
					} catch (InvalidCritterException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;

			case "step" :
				int steps = 1;
				if(arguments.length>2) {
					System.out.println("invalid command: " + input);
				}
				else { 
					try {
						steps = Integer.parseInt(arguments[1]); 
					}
					catch(ArrayIndexOutOfBoundsException e){
						steps=1;
					}
				}
				for(int i = 0; i < steps; i++) {
					Critter.worldTimeStep();
				}

				break;

			case "seed" :
				if(arguments.length != 2) {
					System.out.println("invalid command: " + input);
				}
				else {
					long seed = Integer.parseInt(arguments[1]);
					Critter.setSeed(seed);
				}

				break;

			case "make" :
				if(arguments.length != 3) {
					System.out.println("invalid command: " + input);
				}
				else {
					try {
						for(int c = 0; c < Integer.parseInt(arguments[2]); c++ ) {

							Critter.makeCritter(arguments[1]);

						}
					}

					catch(Exception e){
						System.out.println("error processing: "+ input);
					}

				}
				break;

			case "stats" :
				if(arguments.length != 2) {
					System.out.println("invalid command: " + input);
				}

				else {
					try {
						List<Critter> stats = Critter.getInstances(arguments[1]);
						Class newCritter = Class.forName(arguments[1]);
						Class[] paramList = new Class[1];
						
						paramList[0] = java.util.List.class;				
						
						Method m = newCritter.getMethod("runStats", paramList);
						
						m.invoke(newCritter, stats);
						
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
