package cli;

import java.io.PrintStream;
import java.util.Scanner;
import entropy.StrengthMeter;

public class StrengthMeterPrompter {
	
	private final PrintStream out;
	
	private final StrengthMeter meter; //injecting a StrengthMeter object
	
		
	public StrengthMeterPrompter(Scanner in, PrintStream out, StrengthMeter meter) {
		this.out = out; // immutable variables for safety
		this.meter = meter;
	}
	
	public double strengthMeterString(String userPassword) {
		double entropy = meter.entropy(userPassword);
		String result = "not evalauted";
		
		if (entropy < 40) {
			result = "weak";
		} else if (entropy < 60) {
			result = "fair";
		} else if (entropy < 80) {
			result = "good";
		} else {
			result = "excellent";
		}
		
		out.println(userPassword + " is " + result + " with an entropy value of " 
		+ entropy + ".");
		out.println(meter.toString());
		return entropy;
	}
}
