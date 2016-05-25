/*
 * Name:	David Curry
 * Date:	May 22, 2016
 */
package hellochange.exception;


 /**
  * Exception whenever exact change cannot be made.
  */
public class ExactChangeException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Amount of funds needed. */
	private double amount;
	
	/**
	 * Default constructor.
	 * @param amount Amount of funds needed.
	 */
	public ExactChangeException(double amount) {
		this.amount = amount;
	} //-end constructor
	
	/**
	 * Return amount of funds needed.
	 * @return Amount of funds needed.
	 */
	public double getAmount() {
		return amount;
	} //-end getAmount
	
} //-end 
