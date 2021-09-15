import java.util.ArrayList;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 * @semester 2021 Fall
 * @class CMSC 204
 * @assignment 1_Password Checker
 * @author Sungmin Kim
 * @date 2021/09/013
 */
public class PasswordCheckerUtility {
	
	/**	Constructor */
	public PasswordCheckerUtility() {
	}

	/**	Compares two passwords whether they are matched without return
	 * 	@param password  First password to be checked
	 * 	@param passwordConfirm  Second password to be checked
	 * 	@throws UnmatchedException  Throws if they are not matched. */
	public static void comparePasswords(String password, String passwordConfirm) throws UnmatchedException {
		if (!password.equals(passwordConfirm)) {
			throw new UnmatchedException("Passwords do not match");
		}
	}// end comparePasswords
	
	/**	Compares two passwords whether they are matched with return
	 * 	@param password  First password to be checked
	 * 	@param passwordConfirm  Second password to be checked
	 * 	@return True if they are matched, or false if not. */
	public static boolean comparePasswordsWithReturn(String password, String passwordConfirm) {
		if (password.equals(passwordConfirm)) { return true;}
		else {return false;}
	}// end comparePasswordsWithReturn
	
	/**	Gets invalid passwords from an array list and display the error messages
	 * 	@param passwords  Array list to be checked
	 * 	@return A new array list which collects invalid passwords. */
	public static ArrayList<String> getInvalidPasswords(ArrayList<String> passwords){
		ArrayList<String> tempList = new ArrayList<String>();
		for(int i = 0 ; i < passwords.size() ; i++ ) {
			try {
				isValidPassword(passwords.get(i));
			}catch (Exception e) {
				tempList.add(passwords.get(i) + " -> " + e.getMessage());
			}
		}
		return tempList;
	}// end getInvalidPasswords
	
	/**	Checks if a password has between 6 and 9 characters.
	 * 	@param password  To be checked
	 * 	@return True if the password has between 6 and 9 characters, or false if not. */
	public static boolean hasBetweenSixAndNineChars(String password) {
		if (password.length() >5 && password.length() < 10) { return true; }
		else { return false;}	
	}//end hasBetweenSixAndNineChars
	
	/**	Checks if a password has digits.
	 * @param password To be checked
	 * @return True if the password has digits
	 * @throws NoDigitException  Throws if the password does not have any digits and passes error message. */
	public static boolean hasDigit(String password) throws NoDigitException {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= '0' && password.charAt(i) <= '9') 
				return true;
		}
		throw new NoDigitException("The password must contain at least one digit");
	}// end hasDigit
	
	/**	Checks if a password has lower cases 
	 * @param password  To be checked
	 * @return True if the password has lower cases.
	 * @throws NoLowerAlphaException Throws if the password does not have any lower cases and pass error message. */
	public static boolean hasLowerAlpha(String password) throws NoLowerAlphaException {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'a' && password.charAt(i) <= 'z') 
				return true;
		}
		throw new NoLowerAlphaException("The password must contain at least one lower case alphabetic character");
	}// end hasLowerAlpha
	
	/**	Checks if a password has three same characters in a row.
	 * @param password To be checked
	 * @return False if the password does not have any three same characters in a row.
	 * @throws InvalidSequenceException Throws if the password has three same characters in a row and pass error message. */
	public static boolean hasSameCharInSequence(String password) throws InvalidSequenceException {
		for (int i = 0 ; i < password.length()-2 ; i++) {
			if (password.charAt(i) == password.charAt(i+1)) {
				i++;
				if (password.charAt(i-1) == password.charAt(i+1)) 
					throw new InvalidSequenceException("The password cannot contain more than two of the same character in sequence");
			}
		}
		return false;
	}// end hasSameCharInSequence
	
	/**	Checks if a password has special characters.
	 * @param password To be checked
	 * @return True if the password has special characters.
	 * @throws NoSpecialCharacterException Throws if the password does not have any special characters and pass error message. */
	public static boolean hasSpecialChar(String password) throws NoSpecialCharacterException {
		Pattern pattern = Pattern.compile("[a-zA-Z0-9]*");
		Matcher matcher = pattern.matcher(password);
		if (matcher.matches())
			throw new NoSpecialCharacterException("The password must contain at least one special character");
		return true;
	}// end hasSpecialChar
	
	/**	Checks if a password has upper cases.
	 * @param password To be checked
	 * @return True if the password has upper cases.
	 * @throws NoUpperAlphaException Throws if the password does not have any upper cases and pass error message.
	 */
	public static boolean hasUpperAlpha(String password) throws NoUpperAlphaException {
		for (int i = 0; i < password.length(); i++) {
			if (password.charAt(i) >= 'A' && password.charAt(i) <= 'Z') 
				return true;
		}
		throw new NoUpperAlphaException("The password must contain at least one uppercase alphabetic character");
	}// end hasUpperAlpha
	
	/**	Checks if a password has at least six characters.
	 * @param password To be checked.
	 * @return True if the password has valid length.
	 * @throws LengthException Throws if the password has less than six characters and pass error message. */
	public static boolean isValidLength(String password) throws LengthException{
		if (password.length() > 5) { return true;}
		throw new LengthException("The password must be at least 6 characters long");
	}// end isValidLength
	
	/** Checks if a password is valid.
	 * @param password to be checked
	 * @return True if the password is valid.
	 * @throws LengthException  Throws if the password has less then six characters and pass error message.
	 * @throws NoUpperAlphaException Throws if the password does not have any upper cases and pass error message.
	 * @throws NoLowerAlphaException Throws if the password does not have any lower cases and pass error message.
	 * @throws NoDigitException  Throws if the password does not have any digits and pass error message.
	 * @throws NoSpecialCharacterException  Throws if the password does not have any special characters and pass error message.
	 * @throws InvalidSequenceException  Throws if the password has three same characters in a row and pass error message. */
	public static boolean isValidPassword(String password) throws LengthException, NoUpperAlphaException, NoLowerAlphaException,
																NoDigitException, NoSpecialCharacterException, InvalidSequenceException{
		isValidLength(password);
		hasDigit(password);
		hasLowerAlpha(password);
		hasUpperAlpha(password);
		hasSpecialChar(password);
		hasSameCharInSequence(password);
		return true;
	}// end isValidPassword
	
	/**	Checks if a password is weak, which means it is valid and has between six and nine characters.
	 * @param password To be checked.
	 * @return False if the password is not a weak password.
	 * @throws WeakPasswordException Throws if the password is a weak password. */
	public static boolean isWeakPassword(String password) throws WeakPasswordException{
		if (hasBetweenSixAndNineChars(password)) {
			throw new WeakPasswordException("Password is OK but weak");
		}else
			return false;
	}// end isWeakPassword 
}
