package cli;
import validation.PasswordValidator;
import entropy.StrengthMeter;
import java.io.PrintStream;
import java.util.Scanner;
// CLI (Command-Line Interface)
public class PasswordPrompter {
	private final Scanner in; //encapsulates I/O streams
	private final PrintStream out;
	private final StrengthMeter meter;
	// creates object to call strengthMeterString
	private final StrengthMeterPrompter strengthPrompter; 
	
	public PasswordPrompter(Scanner in, PrintStream out, StrengthMeter meter) {
		this.in = in; // immutable variables for safety
		this.out = out;
		this.meter = meter;
		this.strengthPrompter = new StrengthMeterPrompter(in, out, meter);
	}
	
	public String promptValidPassword() {
		// Extends password evaluation to check character length
		String userPassword;
		out.println("Current password rules:");
		// Prints current password rules
		int counter = 1;
			for (String[] rule: PasswordValidator.RULES) {
				out.println("Rule " + counter + ": " + rule[1]);
				counter++;
			}
		do {
			// -- (Driver) password prompt
			System.out.println();
			out.print("Please enter your password: ");
			userPassword = in.nextLine();
			if(!PasswordValidator.isValidLength(userPassword)) {
				out.println("Password must be at least " + 
						PasswordValidator.getPasswordLength() + " characters.");
				//TODO: "94" is hardcoded for possibilities parameter of entropy()
				//TODO: Build a method that determines possibilities in a StrengthMeter class
				out.println("\"" + userPassword + "\" [THEORETICAL] entropy value is " 
						+ meter.entropy(userPassword));
				// call to strengthMeterString
				strengthPrompter.strengthMeterString(userPassword);
				continue; //skips next iteration if isValidLength() fails
			}
			String complexityMessage = PasswordValidator.complexityMessage(userPassword);
			if(!PasswordValidator.isComplex(userPassword)) {
				out.println(complexityMessage);
				//TODO: "94" is hardcoded for possibilities parameter of entropy()
				//TODO: Build a method that determines possibilities in a StrengthMeter class
				out.println("\"" + userPassword + "\" [THEORETICAL] entropy value is " 
						+ meter.entropy(userPassword));
				// call to strengthMeterString
				strengthPrompter.strengthMeterString(userPassword);
			}
		} while (!PasswordValidator.isValidLength(userPassword) 
				|| !PasswordValidator.isComplex(userPassword));
		return userPassword;
	}
}
