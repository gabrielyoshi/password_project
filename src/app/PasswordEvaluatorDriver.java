package app;
import java.util.Scanner;
import java.util.Random;

import cli.UsernamePrompter;
import cli.PasswordPrompter;
import cli.StrengthMeterPrompter;
import username.UsernameGenerator;
import entropy.StrengthMeter;

public class PasswordEvaluatorDriver {
	
	public static void main(String[]arg) {
		Scanner scan = new Scanner(System.in);
		
		// Welcome Screen
		System.out.println("Welcome to Password Evaluator!");
		
		// Injecting a meter object for strength meter evaluation
		StrengthMeter meter = new StrengthMeter();
		
		// Command Line Interfaces
		UsernamePrompter userPrompts = new UsernamePrompter(scan, System.out);
		PasswordPrompter passPrompts = new PasswordPrompter(scan, System.out, meter);
		StrengthMeterPrompter strengthPrompts = new StrengthMeterPrompter(scan, System.out, meter);
		
		String firstName = userPrompts.promptFirstName();
		String lastName = userPrompts.promptLastName();
		
		// Generates Username
		String username = UsernameGenerator.generateUsername(firstName, 
				lastName, new Random());
		System.out.println("Your Random Username is: " + username);
		
		// Validates password
		String userPassword = passPrompts.promptValidPassword();
		
		// TODO: Compare the userPassword to common words/names/patterns/phrases
		
		// Password strength
		
		// Calculates entropy and bruteforce time
		Double entropy = strengthPrompts.strengthMeterString(userPassword);
		
		scan.close();
	}
}
