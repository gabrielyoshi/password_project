package app;
import java.util.Scanner;
import java.util.Random;

import cli.UsernamePrompter;
import cli.PasswordPrompter;
import username.UsernameGenerator;
import validation.PasswordValidator;

public class PasswordEvaluatorDriver {
	
	public static void main(String[]arg) {
		Scanner scan = new Scanner(System.in);
		
		// Welcome Screen
		System.out.println("Welcome to Password Evaluator!");
		
		// Command Line Interfaces
		UsernamePrompter userPrompts = new UsernamePrompter(scan, System.out);
		PasswordPrompter passPrompts = new PasswordPrompter(scan, System.out);
		
		String firstName = userPrompts.promptFirstName();
		String lastName = userPrompts.promptLastName();
		
		// Generates Username
		String username = UsernameGenerator.generateUsername(firstName, 
				lastName, new Random());
		System.out.println("Your Random Username is: " + username);
		
		String userPassword = passPrompts.promptValidPassword();
		
		// TODO: Compare the userPassword to common words/names/patterns/phrases
		
		scan.close();
	}
}
