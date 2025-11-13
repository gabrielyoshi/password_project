package entropy;

public class StrengthMeter {

	private int possibilities=0;
	private String password = "";
	private static int attemptsPerSecond = 1000000000; // Cracking compute cycles
	// set at 1 billion attempts per second
	private int lastLengthUsed = -1;

	public StrengthMeter() {
		}
	
	private int determineCharsetSize(String password) {
		// initializes booleans
		boolean hasLower = false;
		boolean hasUpper = false;
		boolean hasDigit = false;
		boolean hasSymbol = false;
		
		// loops through password characters to determine classification
		for (char c : password.toCharArray()) {
			if (Character.isLowerCase(c)) {
				hasLower = true;
			} else if (Character.isUpperCase(c)) {
				hasUpper = true;
			} else if (Character.isDigit(c)) {
				hasDigit = true;
			} else {
				hasSymbol = true;
			}
		}
		
		int size = 0;
		if (hasLower) size += 26;
		if (hasUpper) size += 26;
		if (hasDigit) size += 10;
		if (hasSymbol) size += 32; //ASCII symbols
		
		return size;
	}
	
	// helper method to calculate logBase2
		public double logBase2(double input) {
			return Math.log(input) / Math.log(2);
		}

	/**
	 * Calculates randomness of a system
	 * NOTE: logarithmic scale — each additional bit doubles the number of 
	 * possible passwords. Also, N is dynamic based on the password.
	 * @param string representing the password
	 * @return entropy in bits
	 */
	public double entropy(String password) {
		this.password = password;
		lastLengthUsed = password.length();
		this.possibilities = determineCharsetSize(password);
		return password.length() * logBase2(this.possibilities);
	}

	
	public static double bruteForceTime(double entropyBits) {
		double totalPossiblePasswords = Math.pow(2,  entropyBits);
		double averageTime = (totalPossiblePasswords / 2) / attemptsPerSecond;
		return averageTime;
	}
	
	public static String secondsToDaysMonthsYears(double secondsInput) {
		long seconds = (long) secondsInput; // truncates fractional seconds
		// type 64-bit long to avoid accidental overflow int limit is ~2.1 billion
		
		long years = seconds / (365L * 24 * 60 * 60);
		seconds %= (365L * 24 * 60 * 60);
		
		long months = seconds / (30L * 24 * 60 * 60); // approximate 30-day month
		seconds %= (30L * 24 * 60 * 60);
		
		long days = seconds / (24L * 60 * 60);
		seconds %= (24L * 60 * 60);
		
		long hours = seconds / (60L * 60);
		seconds %= (60L * 60);
		
		long minutes = seconds / 60;
		seconds %= 60;
		
		return years + " years, "
				+ months + " months, "
				+ days + " days, "
				+ hours + " hours, "
				+ minutes + " minutes, "
				+ seconds + " seconds";
	}
	
	public String getAttemptsPerSecondFormatted() {
		return String.format("%,d", attemptsPerSecond);
	}
	
	private String formatLong(long value) {
		return String.format("%,d", value);
	}
	
	@Override
	public String toString() {
		String lengthInfo = (lastLengthUsed == -1)
				? "No length calculated yet"
						: String.valueOf(lastLengthUsed);
		
		double ent = (lastLengthUsed == -1) ? 0 : entropy(this.password);
		double seconds = (lastLengthUsed == -1) ? 0 : bruteForceTime(ent);
		String readable = (lastLengthUsed == -1) ? "N/A" : secondsToDaysMonthsYears(seconds);
		
		return "StrengthMeter Settings:\n" +
				"Charset size: " + possibilities + "\n" +
				"Attempts per second: " + getAttemptsPerSecondFormatted() + "\n" +
				"Last length used: " + lengthInfo + "\n" +
				String.format("Entropy: %.2f bits\n",  ent) +
				"Average brute-force time: " + seconds + " seconds\n" +
				"Readable time: " + readable;
	}
	
	public int getCharsetSize() {
		return possibilities;
	}
	
	public void setAttemptsPerSecond(int input) {
		attemptsPerSecond = input;
	}

}
