package hellochange.test;


import static org.junit.Assert.assertTrue;

import org.junit.Test;

import hellochange.Denomination;
import hellochange.Register;
import hellochange.exception.ExactChangeException;
import hellochange.exception.InsufficientFundsException;
import hellochange.exception.InvalidDenominationException;

/**
 * Test <code>Register
 * @author agentcurry
 *
 */
public class RegisterTest {

	/** Test creation of new <code>Register</code>. */
	@Test
	public void testNewRegister() {
		Register r = new Register();
		
		assertTrue("[RegisterTest] Register denomination not equal to 0", r.getRegisterTotal() == 0);
		assertTrue("[RegisterTest] Register does not account for $20", r.getDrawer().get(0).getDenomination() == 20);
		assertTrue("[RegisterTest] Register does not account for $10", r.getDrawer().get(1).getDenomination() == 10);
		assertTrue("[RegisterTest] Register does not account for $5",  r.getDrawer().get(2).getDenomination() == 5);
		assertTrue("[RegisterTest] Register does not account for $2",  r.getDrawer().get(3).getDenomination() == 2);
		assertTrue("[RegisterTest] Register does not account for $1",  r.getDrawer().get(4).getDenomination() == 1);
	}
	
	
	/** Test <code>Register.put()</code> method. */
	@Test
	public void testPut() throws InvalidDenominationException {
		Register r = new Register();
		
		assertTrue("[RegisterTest] Register.put() denomination not equal to 0", r.getRegisterTotal() == 0);
		r.put(new Denomination(5, 3));
		assertTrue("[RegisterTest] Register.put() not adding",  r.getDrawer().get(2).getQuantity() == 3);
		assertTrue("[RegisterTest] Register.put() total innacurate", r.getRegisterTotal() == 15);
	}
	
	
	/** 
	 * Test <code>Register.change()</code> method where multiple denominations 
	 * are needed to make change.
	 */
	@Test
	public void testExactChange_MultipleDenomination()  throws InvalidDenominationException, InsufficientFundsException, ExactChangeException {
		Register r = new Register();
		
		assertTrue("[RegisterTest] Register.put() denomination not equal to 0", r.getRegisterTotal() == 0);
		r.put(new Denomination(20, 2));
		r.put(new Denomination(10, 2));
		r.put(new Denomination(5, 2));
		r.put(new Denomination(2, 2));
		r.put(new Denomination(1, 2));
		assertTrue("[RegisterTest] Register total is inaccurate ", r.getRegisterTotal() == 76);
		
		
		r.change(3, 50);
		assertTrue("[RegisterTest] Register total, $20 quantity is inaccurate ", r.getDrawer().get(0).getQuantity() == 0);
		assertTrue("[RegisterTest] Register total, $10 quantity is inaccurate ", r.getDrawer().get(1).getQuantity() == 2);
		assertTrue("[RegisterTest] Register total, $5 quantity is inaccurate ", r.getDrawer().get(2).getQuantity() == 1);
		assertTrue("[RegisterTest] Register total, $2 quantity is inaccurate ", r.getDrawer().get(3).getQuantity() == 1);
		assertTrue("[RegisterTest] Register total, $1 quantity is inaccurate ", r.getDrawer().get(4).getQuantity() == 2);
	}
	
	//TODO testExactChange()
	
	//TODO testNotExactChange()
	
} //-
