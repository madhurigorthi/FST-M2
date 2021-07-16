package Activities;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

public class Activity2 {
	
	@Test
	public void notEnoughFunds() {
		BankAccount acc = new BankAccount(100);
		assertThrows(NotEnoughFundsException.class, () -> acc.withdraw(100));
		
	}
	
	@Test
	public void EnoughFunds() {
		BankAccount acc = new BankAccount(100);
		assertDoesNotThrow(() -> acc.withdraw(10));
		
	}

}
