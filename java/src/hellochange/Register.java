/*
 * Name:	David Curry
 * Date:	May 22, 2016
 */
package hellochange;

import java.util.ArrayList;

import hellochange.exception.ExactChangeException;
import hellochange.exception.InsufficientFundsException;
import hellochange.exception.InvalidDenominationException;

/**
 * Represents the register.
 * @author agentcurry
 */
public final class Register {
	
	/** Register drawer contains list of denominations and quantity of each. */
	private ArrayList<Denomination> drawer;
	
	
	
	/** Constructor. */
	public Register() {
		init();
	}
	
    
    /** 
     * Initialization:
     * Create denominations.  The register drawer will hold a 
     * denomination object for each supported denomination, starting 
     * quantity = 0 for each. 
     */
    private void init() {
    	drawer = new ArrayList<Denomination>();
    	
    	// Create denominations from supported denominations
    	for (double denom : Denomination.SUPPORTED_DENOMINATIONS) {
    		  drawer.add(new Denomination(denom, 0));
    	}
    }
    
    
    /** 
     * Return drawer.
     * @return Return drawer.
     */
    public ArrayList<Denomination> getDrawer() {
    	return drawer;
    }
    
    
    /**
     * Return register total.
     * @return Register total.
     */
    public double getRegisterTotal() {
    	double total = 0d;
    	for (Denomination d : drawer) {
    		total += d.getDenomination() * d.getQuantity();
    	}
    	return total;
    }

    
    /**
     * Add denomination quantity to register drawer.
     * @param value Denomination.
     */
    public void put(Denomination value) throws InvalidDenominationException {
    	
    	for (Denomination denom : drawer) {
    		if (denom.getDenomination() == value.getDenomination()) {
    			denom.addQuantity(value.getQuantity());
    			return;
    		}
    	}
    	
    	// If denominator not in drawer, unsupported, throw an exception
    	throw new InvalidDenominationException(value.getDenomination());
    } //-
    
    
    /**
     * Returns change for a given charge and payment.  If exact change is not
     * possible, an ExactChangeException is thrown.
     * @param charge Amount charged.
     * @param payment Amount payed.
     * @return Payment change.
     */
    public Change change(double charge, double payment) throws ExactChangeException, InsufficientFundsException {
    	
    	// error if payment is less than charge 
    	if (payment < charge) {
    		throw new InsufficientFundsException(charge, payment);
    	}
    	
    	// error if register total is less than change 
    	double change = payment - charge;
    	if (getRegisterTotal() < change) {
    		throw new ExactChangeException(change);
    	}
    	
    	//check for exact denomination change available
    	Change changeToReturn = new Change(change);
    	for (Denomination d : drawer) {
    		if (d.getQuantity() > 0) {
    			if (d.getDenomination() == change) {
    				changeToReturn.getChangeToReturn().add(new Denomination(d.getDenomination(), 1));
    				// deduct from drawer
    		    	deduct(changeToReturn.getChangeToReturn());
    				return changeToReturn;
    			}
    		}
    	}
    	
    	// check for exact change with multiple denominations
    	changeSearch(change, Denomination.SUPPORTED_DENOMINATIONS, 0, changeToReturn);
    	
    	// check if exact change was found
    	if (changeToReturn.getRemainingChange() == 0) {
    		// deduct from drawer
        	deduct(changeToReturn.getChangeToReturn());
    	} else {
    		throw new ExactChangeException(change);
    	}
    	
    	// return change
    	return changeToReturn;
    
    } //- end change()
    
    
    /**
     * Recursive method to determine if exact change is available.  Advances through 
     * each denomination in register drawer, then determines if denomination
     * is enough to add to change. 
     */
    private void changeSearch(double remainingChange, double[] denominationToSearch, 
    		int denominationToSearch_index, Change changeToReturn) {
    	
    	//--- TETSTING ------------------------------
    	System.out.println("\n\n");
    	int recusion = denominationToSearch_index+1;
    	System.out.println("Recurssion = " + recusion);
    	System.out.println("changeToReturn :");
    	for (Denomination d : changeToReturn.getChangeToReturn()) {
    		System.out.println("\t $" + d.getDenomination() + " x " + d.getQuantity());
    	}
    	System.out.println("---");
    	System.out.println("remainingChange = " + remainingChange);
    	System.out.println("denominationToSearch = " + denominationToSearch[denominationToSearch_index]);
    	System.out.println("-------------------");
    	
    	
    	// check if no remaining change
    	if (remainingChange == 0) 
    		return;
    	
    	// check if no more denominations to search
    	if (denominationToSearch_index == denominationToSearch.length-1) 
    		return;
    	
    	// check if denomination has quantity available
    	Denomination drawerDenomination = drawer.get(denominationToSearch_index);
    	if (drawerDenomination.getQuantity() == 0) {
    		changeSearch(remainingChange, denominationToSearch, denominationToSearch_index+1, changeToReturn);
    		
    	} else {
    		
    		// add current denomination if remainingChange is >= drawerDenomination
    		Denomination tmp = null;
    		for (int i=1; i<=drawerDenomination.getQuantity(); i++) {
    			if (remainingChange >= drawerDenomination.getDenomination()) {
    				if (tmp == null) {
    					tmp = new Denomination(drawerDenomination.getDenomination(), 1);
    				} else {
    					tmp.setQuantity(tmp.getQuantity()+1); 
    				}
    				remainingChange -= drawerDenomination.getDenomination();
    			}
    		}
    		if (tmp != null) {
    			// set remainingChange.  add current denomination to changeToReturn
    			changeToReturn.getChangeToReturn().add(tmp);
    		}
    		
    	} //- end if
    	
    	changeSearch(remainingChange, denominationToSearch, denominationToSearch_index+1, changeToReturn);
    
    } //- end changeSearch
    
    
    /**
     * Deduct denominations from drawer.
     * @param drawer
     */
    private void deduct(ArrayList<Denomination> changeToReturn) {
    	
    	for (Denomination drawer_d : drawer) {
    		for (Denomination change_d : changeToReturn) {
    			if (drawer_d.getDenomination() == change_d.getDenomination()) {
    				drawer_d.setQuantity(drawer_d.getQuantity()-change_d.getQuantity());
    			}
    		}
    	}
    }
    
} //- end
