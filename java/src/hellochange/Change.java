/*
 * David Curry
 * May 23, 2016
 */
package hellochange;

import java.util.ArrayList;

/**
 * Change to return to customer.
 * @author agentcurry
 */
public class Change {

	/** Contains list of denominations and quantity of each to makeup change. */
	private ArrayList<Denomination> changeToReturn;

	/** Total change due to customer. */
	private double changeDue;
	
	/**
	 * Constructor
	 * @param changeDue Change due to customer.
	 */
	public Change(double changeDue) {
		this.changeDue = changeDue;
		changeToReturn = new ArrayList<Denomination>();
	}
	
	public ArrayList<Denomination> getChangeToReturn() {
		return changeToReturn;
	}
	
	public double getChangeDue() {
		return changeDue;
	}
	
	/**
	 * Return the amount of change still due.
	 * @return Remaining change.
	 */
	public double getRemainingChange() {
		double totalChangeToReturn = 0d;
		for (Denomination d : changeToReturn) {
			totalChangeToReturn += (d.getDenomination() * d.getQuantity());
		}
		return changeDue - totalChangeToReturn;
	}
	
	
} //-
