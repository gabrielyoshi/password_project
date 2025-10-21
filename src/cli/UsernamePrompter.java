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
	/**
	 * Prompts user for first name
	 * @return string first name
	 */
	public String promptFirstName() {
		// First Name input and validation
		String firstName;
		do {
			out.println("First, let's generate a username!");
			out.print("Please enter your first name: ");
			firstName = in.nextLine().trim();
			if (!UsernameGenerator.isValidFirstName(firstName)) {
				out.println("Invalid first name. It must start with a"
						+ "letter and cannot be empty.");
			}
		} while (!UsernameGenerator.isValidFirstName(firstName));
		return firstName;
	}
	
	/**
	 * Prompts user for last name
	 * @return string user last name
	 */
	public String promptLastName() {
		// Last Name input and validation
		String lastName;
		do {
			out.print("Please enter your last name (no spaces): ");
			lastName = in.nextLine().trim();
			if (!UsernameGenerator.isValidLastName(lastName) ) {
				out.println("Invalid last name. No spaces allowed.");
			}
		} while (!UsernameGenerator.isValidLastName(lastName));
		return lastName;
		
	}
}
