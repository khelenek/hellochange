/*
 * Name:	David Curry
 * Date:	May 22, 2016
 */
package hellochange;

/**
 * Represents money denomination.
 * @author agentcurry
 */
public class Denomination {

	public static final double[] SUPPORTED_DENOMINATIONS = new double[] {20, 10, 5, 2, 1};
	
	/** Number of denomination on hand. */
	private int quantity;
	
	/** Money denominations (eg $5, $20, etc). */
	private double denomination;

	
	
	/** 
	 * Parameterized constructor.
	 * @param denomination Denomination amount.
	 * @param quantity Quantity of denomination. 
	 */
	public Denomination(double denomination, int quantity) {
		this.denomination = denomination;
		this.quantity = quantity;
	}
	

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public double getDenomination() {
		return denomination;
	}

	public void setDenomination(int denomination) {
		this.denomination = denomination;
	}
	
	/** Add to current quantity. */
	public void addQuantity(int quantity) {
		this.quantity += quantity;
	}
	
	/** Return total amount for the denomination. */
	public double getTotal() {
		return getDenomination() * getTotal();
	}
	
} //- end
