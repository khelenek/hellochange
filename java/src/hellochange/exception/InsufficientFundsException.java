/*
 * Name:	David Curry
 * Date:	May 23, 2016
 */
package hellochange.exception;


 /**
  * Exception for insufficient funds.
  */
public class InsufficientFundsException extends Exception {

	private static final long serialVersionUID = 1L;
	
	/** Total charge due. */
	private double charge;
	/** Total amount paid. */
	private double paid;
	
	/**
	 * Constructor.
	 * @param charge Total charge due.
	 * @param paid Total amount paid.
	 */
	public InsufficientFundsException(double charge, double paid) {
		this.charge = charge;
		this.paid = paid;
	} 
	
	/**
	 * Return total charge due.
	 * @return Total charge due.
	 */
	public double getCharge() {
		return charge;
	}
	
	/**
	 * Return total paid.
	 * @return Total paid.
	 */
	public double getPaid() {
		return paid;
	}
	
	/** 
	 * Return amount due for full payment.
	 * @return Amount due.
	 */
	public double getDue() {
		return charge - paid;
	}
	
} //-end 
