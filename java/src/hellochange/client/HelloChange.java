/*
 * Name:	David Curry
 * Date:	May 22, 2016
 */
package hellochange.client;

import java.util.Scanner;

import hellochange.Denomination;
import hellochange.Register;
import hellochange.exception.ExactChangeException;
import hellochange.exception.InsufficientFundsException;
import hellochange.exception.InvalidDenominationException;

/**
 * Application entry point.
 * @author agentcurry
 */
public class HelloChange {

	private static Scanner scanner = new Scanner(System.in);
	private static Register register = new Register();
	
	
	/** Entry point for application. */
	public static void main(String[] args) {
		ready();
	}
	
	
	/** Initialize application. */
	private static void ready() {
		System.out.println("ready");
		readUserInput();
	}
	
	
	/** Show current state of the register */
	private static void show() {
		StringBuffer sb = new StringBuffer();
		
		// iterate through register drawer 
		for (Denomination denom : register.getDrawer()) {
			sb.append("$").append(String.format( "%.2f", denom.getDenomination()));
			sb.append("\t").append(denom.getQuantity()).append("\n");
		}
		System.out.println(sb.toString());
		readUserInput();
	}
	
	
	/** 
	 * Add denomination quantity to register drawer. 
	 * @param denomination Denomination amount.
	 * @param quantity Denomination quantity.
	 */
	private static void put(int denomination, int quantity) {
		try {
			register.put(new Denomination(denomination, quantity));
		} catch (InvalidDenominationException e) {
			System.out.println("Invalid denomination: " + e.getDenominationAmount());
			readUserInput();
		}
		show();
	}
	
	
	/**
	 * Make a payment and return change.
	 * @param charge Amount of charge.
	 * @param paid Amount paid.
	 */
	private static void change(double charge, double payment) {
		try {
			register.change(charge, payment);
		} catch (InsufficientFundsException e) {
			System.out.println("Insufficient funds: $" + String.format( "%.2f", e.getDue()) + " due");
			readUserInput();
		} catch (ExactChangeException ce) {
			System.out.println("Sorry");
			readUserInput();
		}
		show();
	}
	

	/** Close application. */
	private static void quit() {
		System.out.println("bye");
		System.exit(0);
	}
	
	
	/** 
	 * Controller for user input.  Reads the input, then 
	 * routes to corresponding method.
	 */
	private static void readUserInput() {
		// get user input
		String input = scanner.nextLine();
		String[] inputSplit = null;
		String command = "";
		
		// read first part of input for command (show, put, change, quit)
		if (input.length() == 0) {
			System.out.println("Error - Invalid command.");
			readUserInput();
		}
		inputSplit = input.split("\\s+");
		command = inputSplit[0];
		
		
		// route command to corresponding method
		switch (command) {
	        case "show": 
	        	show();
	        	break;
	                 
	        case "put":
	        	try {
	        		put(Integer.valueOf(inputSplit[2]), Integer.valueOf(inputSplit[1]));
	        	} catch (ArrayIndexOutOfBoundsException ae) {
	        		System.out.println("Invalid command.");
	        		readUserInput();
	        	} catch(NumberFormatException e) {
	        		System.out.println("Invalid command.");
	        		readUserInput();
	        	}
	        	break;
	                 
	        case "change":
	        	try {
	        		change(Integer.valueOf(inputSplit[1]), Integer.valueOf(inputSplit[2]));
	        	} catch (ArrayIndexOutOfBoundsException ae) {
	        		System.out.println("Invalid command.");
	        		readUserInput();
	        	} catch(NumberFormatException e) {
	        		System.out.println("Invalid command.");
	        		readUserInput();
	        	}
	        	break;
	        
	        case "quit":
	        	quit();
	        	break;
	
	        default: 
	        	System.out.println("Invalid command.");
	        	readUserInput();
	        	break;
		} //- end switch
		
	} //-
	
} //- end
