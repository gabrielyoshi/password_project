package cli;
import validation.PasswordValidator;
import java.io.PrintStream;
import java.util.Scanner;
// CLI (Command-Line Interface)
public class PasswordPrompter {
	private final Scanner in; //encapsulates I/O streams
	private final PrintStream out;
	
	public PasswordPrompter(Scanner in, PrintStream out) {
		this.in = in; // immutable variables for safety
		this.out = out; 
	}
	
	public String promptValidPassword() {
		// Extends password evaluation to check character length
		String userPassword;
		do {
			// -- (Driver) password prompt
			out.print("Please enter your password: ");
			userPassword = in.nextLine();
			if(!PasswordValidator.isValidLength(userPassword)) {
				out.println("Password must be at least " + 
						PasswordValidator.getPasswordLength() + " characters.");
				continue; //skips next iteration if isValidLength() fails
			}
			String complexityMessage = PasswordValidator.complexityMessage(userPassword);
			if(!PasswordValidator.isComplex(userPassword)) {
				out.println(complexityMessage);
			}
		} while (!PasswordValidator.isValidLength(userPassword) 
				|| !PasswordValidator.isComplex(userPassword));
		return userPassword;
	}
}
