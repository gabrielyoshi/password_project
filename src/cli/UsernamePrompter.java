package cli;
import username.UsernameGenerator;
import java.io.PrintStream;
import java.util.Scanner;
//CLI (Command-Line Interface)

public class UsernamePrompter {
	private final Scanner in; //encapsulates I/O streams
	private final PrintStream out;
	
	public UsernamePrompter(Scanner in, PrintStream out) {
		this.in = in; // immutable variables for safety
		this.out = out;
	}
	
	public String promptFirstName() {
		// First Name input and validation
		String firstName;
		do {
			System.out.println("First, let's generate a username!");
			System.out.print("Please enter your first name: ");
			firstName = in.nextLine().trim();
			if (!UsernameGenerator.isValidFirstName(firstName)) {
				System.out.println("Invalid first name. It must start with a"
						+ "letter and cannot be empty.");
			}
		} while (!UsernameGenerator.isValidFirstName(firstName));
		return firstName;
	}
	
	public String promptLastName() {
		// Last Name input and validation
		String lastName;
		do {
			System.out.print("Please enter your last name (no spaces): ");
			lastName = in.nextLine().trim();
			if (!UsernameGenerator.isValidLastName(lastName) ) {
				System.out.println("Invalid last name. No spaces allowed.");
			}
		} while (!UsernameGenerator.isValidLastName(lastName));
		return lastName;
		
	}
}
