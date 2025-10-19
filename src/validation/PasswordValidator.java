package validation;

import java.util.regex.Pattern;
import java.util.List;
import java.util.ArrayList;

public class PasswordValidator {

	private static final int MIN_LENGTH = 8; //sets password length at 8 char
	
	// Regex variables
	private static final String AT_LEAST_ONE_UPPERCASE = "(?=.*[A-Z])";
	private static final String AT_LEAST_ONE_LOWERCASE = "(?=.*[a-z])";
	private static final String AT_LEAST_ONE_DIGIT = "(?=.*\\d)";
	private static final String AT_LEAST_ONE_SPECIAL = "(?=.*[^A-Za-z\\d])";
	
	/**
	 * Checks if password fulfills the following:
	 * - At least one uppercase letter
	 * - At least one lowercase letter
	 * - At least one digit
	 * - At least one special character
	 * @param password
	 * @return string message
	 */
	
	public static String complexityMessage(String password) {
		if (password == null) return "Password is null.";
		
		// Combine regex rules and description into 2-string array (4x2 structure)
		// in a "rules" list. rules[0] is regex string, rules[1] is description
		List<String[]> rules = List.of(
			new String[] {AT_LEAST_ONE_UPPERCASE, "an uppercase letter"},
			new String[] {AT_LEAST_ONE_LOWERCASE, "a lowercase letter"},
			new String[] {AT_LEAST_ONE_DIGIT, "a digit"},
			new String[] {AT_LEAST_ONE_SPECIAL, "a special character"}
		);
		
		// stores description single elements that fail test
		List<String> missing = new ArrayList<>();
		
		for (String[] rule : rules) {
			// compiles new regex pattern object
			// TODO: relatively expensive. Should be compiled once and reused
			Pattern pattern = Pattern.compile("^" + rule[0] + ".*$");
			// tests if password string matches regex stored pattern
			if(!pattern.matcher(password).matches()) {
				missing.add(rule[1]); // adds failed description to "missing"[]
			}
		}
		
		// Returns feedback message 
		if (missing.isEmpty()) return "valid"; // password passes tests
		if (missing.size() == 1) return "Password must contain " + missing.get(0) 
			+ "."; // one failure
		String last = missing.remove(missing.size() - 1); // stores last element
		return "Password must contain " + String.join(", ", missing) + ", and " 
			+ last + ".";
	}
	/**
	 * Logical-only check with boolean return
	 * @param password
	 * @return boolean value
	 */
	public static boolean isComplex(String password) {
		return "valid".equals(complexityMessage(password));
	}
		
	public static int getPasswordLength() { return MIN_LENGTH; }
	
	/**
	 * Checks if password meets minimum length requirement
	 * @param password
	 * @return boolean value
	 */
	public static boolean isValidLength(String password) {
		return password != null && password.length() >= MIN_LENGTH;
	}
}
