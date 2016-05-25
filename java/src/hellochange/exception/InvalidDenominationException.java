/*
 * Name:	David Curry
 * Date:	May 22, 2016
 */
package hellochange.exception;


 /**
  * Exception for unsupported denomination.
  */
public class InvalidDenominationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Unsupported denomination amount. */
	private double denomination;
	
	/**
	 * Constructor.
	 * @param amount Unsupported denomination.
	 */
	public InvalidDenominationException(double denomination) {
		this.denomination = denomination;
	} 
	
	/**
	 * Return unsupported denomination amount.
	 * @return Unsupported denomination amount.
	 */
	public double getDenominationAmount() {
		return denomination;
	}
	
} //-end 
