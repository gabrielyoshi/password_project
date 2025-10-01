import java.util.Scanner;
import java.util.Random;

/*public class PasswordLength {
	// variable declarations of array of strings

}*/

public class Password_Evaluator {
	
	public static void main(String[]arg) {
		Scanner scan = new Scanner(System.in);
		
		// Welcome Screen
		System.out.println("Welcome to Password Evaluator!");
		
		// Collect and validate names using UsernameGenerator
		
		// First Name input and validation
		String firstName;
		do {
			System.out.println("First, let's generate a username!");
			System.out.print("Please enter your first name: ");
			firstName = scan.nextLine().trim();
			if (!UsernameGenerator.isValidFirstName(firstName)) {
				System.out.println("Invalid first name. It must start with a"
						+ "letter and cannot be empty.");
			}
		} while (!UsernameGenerator.isValidFirstName(firstName));
		
		// Last Name input and validation
		String lastName;
		do {
			System.out.print("Please enter your last name (no spaces): ");
			lastName = scan.nextLine().trim();
			if (!UsernameGenerator.isValidLastName(lastName) ) {
				System.out.println("Invalid last name. No spaces allowed.");
			}
		} while (!UsernameGenerator.isValidLastName(lastName));
		
		// Generate username
		
		String username = UsernameGenerator.generateUsername(firstName, 
				lastName, new Random());
		System.out.println("Your Random Username is: " + username);
		
		// -- (Driver) password prompt; TODO extend password evaluation
		
		System.out.print("Please enter your password: ");
		String userPassword = scan.nextLine();
		
		
		// Compare the userPassword to common words/names/patterns/phrases
		
		scan.close();
		
		
	}

}
