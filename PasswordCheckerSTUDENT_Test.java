
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.function.Executable;

/**
 * STUDENT tests for the methods of PasswordChecker
 * @author Sungmin Kim
 * @date 2021/09/13
 */
public class PasswordCheckerSTUDENT_Test {

	ArrayList<String> invalidPasswordsArray;
	ArrayList<String> validPasswordsArray;
	String password = "Kim";
	String passwordConfirm = "kim";
	String allCaps = "SUNGMIN";
	String withDigit = "Sungmin007";
	String withSpecialChar = "Sungmin!";
	String withNoDuplicate = "Sungmin!";
	String between6And9Chars = "SMJacob";
	String longPassword = "SungminJacobKim";

	
	@Before
	public void setUp() throws Exception {              
		String[] invalidPasswords = {"112233SS", "Sungmin007", "JacobKim8633", "Kim's", "sungmin007", "ILoveCS2much", 
				"ihave1sister", "SungminKim", "Jacob", "Jacob'sHW", "JacobPassword", 
				"JacobPassword2"};
		invalidPasswordsArray = new ArrayList<String>();
		invalidPasswordsArray.addAll(Arrays.asList(invalidPasswords));		
		
		String[] allValidPasswords = {"Sungmin007!", "Jacob8633@", "4Kim'sfamily", "Il0veCSmajor!", "100%Korean"};
		validPasswordsArray = new ArrayList<String>();
		validPasswordsArray.addAll(Arrays.asList(allValidPasswords));	
	}

	@After
	public void tearDown() throws Exception {
		invalidPasswordsArray = null;
		validPasswordsArray= null;
	}

	/**
	 * Test if the password is less than 6 characters long.
	 * This test should throw a LengthException for second case.
	 */
	@Test
	public void testIsValidPasswordTooShort()
	{
		Throwable exception = Assertions.assertThrows(LengthException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.isValidLength(password);
			}			
		});
		assertEquals("The password must be at least 6 characters long", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one uppercase alpha character
	 * This test should throw a NoUpperAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoUpperAlpha()
	{
		Throwable exception = assertThrows(NoUpperAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasUpperAlpha(passwordConfirm);
			}			
		});
		assertEquals("The password must contain at least one uppercase alphabetic character", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one lowercase alpha character
	 * This test should throw a NoLowerAlphaException for second case
	 */
	@Test
	public void testIsValidPasswordNoLowerAlpha()
	{
		Throwable exception = assertThrows(NoLowerAlphaException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasLowerAlpha(allCaps);
			}			
		});
		assertEquals("The password must contain at least one lower case alphabetic character", exception.getMessage());
	}
	/**
	 * Test if the password has more than 2 of the same character in sequence???
	 * This test should throw a InvalidSequenceException for second case???
	 */
	@Test
	public void testIsWeakPassword()
	{
		assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(between6And9Chars));	
		assertTrue(PasswordCheckerUtility.hasBetweenSixAndNineChars(withSpecialChar));	
		assertFalse(PasswordCheckerUtility.hasBetweenSixAndNineChars(longPassword));
	}
	
	/**
	 * Test if the password has more than 2 of the same character in sequence
	 * This test should throw a InvalidSequenceException for second case
	 */
	@Test
	public void testIsValidPasswordInvalidSequence()
	{
		Throwable exception = assertThrows(InvalidSequenceException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasSameCharInSequence("Jacob886333");
			}			
		});
		assertEquals("The password cannot contain more than two of the same character in sequence", exception.getMessage());
	}
	
	/**
	 * Test if the password has at least one digit
	 * One test should throw a NoDigitException
	 */
	@Test
	public void testIsValidPasswordNoDigit()
	{
		Throwable exception = assertThrows(NoDigitException.class, new Executable() {
			@Override
			public void execute() throws Throwable {
				PasswordCheckerUtility.hasDigit(password);
			}			
		});
		assertEquals("The password must contain at least one digit", exception.getMessage());
	}
	
	/**
	 * Test correct passwords
	 * This test should not throw an exception
	 */
	@Test
	public void testIsValidPasswordSuccessful()
	{
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(validPasswordsArray);
		assertTrue(results.isEmpty());
	}
	
	/**
	 * Test the invalidPasswords method
	 * Check the results of the ArrayList of Strings returned by the validPasswords method
	 */
	@Test
	public void testInvalidPasswords() {     
		ArrayList<String> results;
		results = PasswordCheckerUtility.getInvalidPasswords(invalidPasswordsArray);
		assertEquals(results.size(), 12);
		assertEquals(results.get(0), "112233SS -> The password must contain at least one lower case alphabetic character");
		assertEquals(results.get(1), "Sungmin007 -> The password must contain at least one special character");
		assertEquals(results.get(2), "JacobKim8633 -> The password must contain at least one special character");
		assertEquals(results.get(3), "Kim's -> The password must be at least 6 characters long");
		assertEquals(results.get(4), "sungmin007 -> The password must contain at least one uppercase alphabetic character"); 
		assertEquals(results.get(5), "ILoveCS2much -> The password must contain at least one special character");
		assertEquals(results.get(6), "ihave1sister -> The password must contain at least one uppercase alphabetic character");
		assertEquals(results.get(7), "SungminKim -> The password must contain at least one digit");
		assertEquals(results.get(8), "Jacob -> The password must be at least 6 characters long");
		assertEquals(results.get(9), "Jacob'sHW -> The password must contain at least one digit");
		assertEquals(results.get(10), "JacobPassword -> The password must contain at least one digit");
		assertEquals(results.get(11), "JacobPassword2 -> The password must contain at least one special character");
	}
	
}