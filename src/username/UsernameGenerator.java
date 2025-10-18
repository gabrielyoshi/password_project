package username;
import java.util.Random;

public class UsernameGenerator {
			
	//Part one: Get a Valid First and Last Name 
		
	// Validate first name: non-empty, !null reference, and starts with a letter
	public static boolean isValidFirstName(String firstName) {
		if (firstName == null || firstName.isEmpty()) return false;
		return Character.isLetter(firstName.charAt(0));
	}
	
	// Validate last name: non-empty, !null reference, and contains no whitespace
	public static boolean isValidLastName(String lastName) {
		if (lastName == null || lastName.isEmpty()) return false;
		for (int i = 0; i < lastName.length(); i++) {
			if (Character.isWhitespace(lastName.charAt(i))) return false;
		}
		return true;
	}
	
	// Username Generator: first name first letter + last name first 5 char
	// + random two-digit number
	
	public static String generateUsername(String firstName, String lastName,
			Random num) {
		if (!isValidFirstName(firstName) || !isValidLastName(lastName)) {
			throw new IllegalArgumentException("Invalid first or last name.");
		}
		
		
		//initialize firstNameChar
		char firstNameChar = Character.toLowerCase(firstName.charAt(0));
		
		String lastNameLower = lastName.toLowerCase();
		
		String lastNamePart = (lastNameLower.length() >= 5) ? 
				lastNameLower.substring(0, 5) : lastNameLower;
		
		int twoIntegers = num.nextInt(90) + 10; // 10...99
		
		return Character.toString(firstNameChar) + lastNamePart + twoIntegers;
	}

}
