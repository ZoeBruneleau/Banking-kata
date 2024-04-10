import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() {
        account = new Account();
    }

    @Test
    public void testDepositPositiveAmount() {
        account.deposit(500.55);
        assertEquals("After depositing a positive amount, the balance should be updated.", 500.55, account.getBalance(),0.001);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testDepositNegativeAmount() {
        account.deposit(-100.00);
    }

    @Test
    public void testRemoveSufficientBalance() throws InsufficientFundsException {
        account.deposit(500.55);
        account.remove(300.00);
        assertEquals("After removing an amount, the balance should be updated.", 200.55, account.getBalance(),0.001);
    }

    @Test(expected = InsufficientFundsException.class)
    public void testRemoveInsufficientBalance() throws InsufficientFundsException {
        account.deposit(200.00);
        account.remove(300.00);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNegativeAmount() throws InsufficientFundsException {
        account.remove(-100.00);
    }

    @Test
    public void testPrintStatement() {
        account.deposit(500.00);
        try {
            account.remove(100.00);
        } catch (InsufficientFundsException e) {
            fail("InsufficientFundsException should not be thrown.");
        }
        String expectedStatementStart = "Date\t\tAmount\tBalance\n";
        assertTrue("The statement should start with the header including Date, Amount, and Balance.",
                account.toString().startsWith(expectedStatementStart));
        assertTrue("The statement should contain the deposit transaction.",
                account.toString().contains("+500"));
        assertTrue("The statement should contain the remove transaction.",
                account.toString().contains("-100"));
    }
}

