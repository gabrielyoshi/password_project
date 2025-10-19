package test;

import validation.PasswordValidator;
import username.UsernameGenerator;
import java.util.Random;

public class PasswordEvaluatorTestDriver {
	public static void main(String[] args) {
		System.out.println("Running PasswordEvaluator tests...");
		
		testPasswordLength();
		testUsernameGenerator();
		testComplexPassword();
	}
	
	private static void testPasswordLength() {
		assert PasswordValidator.isValidLength("abcd1234");
		assert !PasswordValidator.isValidLength("abc");
		System.out.println("Password length tests passed");
	}
	
	private static void testUsernameGenerator() {
		String user = UsernameGenerator.generateUsername("Gabriel", "Gutierrez", new Random());
		assert user != null && !user.isEmpty();
		System.out.println("Username generator test passed");
	}
	
	private static void testComplexPassword() {
		String goodpassword = PasswordValidator.complexityMessage("Goodpassword1!");
		assert goodpassword != null && goodpassword.equals("valid") :
			"Expected valid password, but got: " + goodpassword;
		
		String upper = PasswordValidator.complexityMessage("nouppercase1!");
		assert upper != null && upper.equals("Password must contain an "
				+ "uppercase letter.") :
				"Expected valid password, but got: " + upper;
		
		String lower = PasswordValidator.complexityMessage("NOLOWERCASE1!");
		assert lower != null && lower.equals("Password must contain a lowercase "
				+ "letter.") :
				"Expected valid password, but got: " + lower;
		
		String special = PasswordValidator.complexityMessage("Nospecialchar");
		assert special != null && special.equals("Password must contain a "
				+ "digit, and a special character.") :
				"Expected valid password, but got: " + special;
		
		String noUpperNoSpecial = PasswordValidator.complexityMessage("nouppernospecial");
		assert noUpperNoSpecial != null && noUpperNoSpecial.equals("Password "
				+ "must contain an uppercase letter, a digit, and a special character.") :
				"Expected valid password, but got: " + noUpperNoSpecial;
		
		System.out.println("testComplexPassword passed");
	}
}
