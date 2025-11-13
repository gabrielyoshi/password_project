package entropy;

public class StrengthMeter {

	private static final int CHARSET_SIZE = 94; // Upper+lower+digits+symbols
	private final int possibilities;
	private static int attemptsPerSecond = 1000000000; // Cracking compute cycles
	// set at 1 billion attempts per second

	public StrengthMeter() {
		this.possibilities = CHARSET_SIZE; // Default constructor
	}

	public StrengthMeter(int possibilities) { // Allows for dynamic "N" value
		this.possibilities = possibilities;
	}

	// helper method to calculate logBase2
	public double logBase2(double input) {
		return Math.log(input) / Math.log(2);
	}

	/**
	 * Calculates randomness of a system
	 * NOTE: logarithmic scale — each additional bit doubles the number of 
	 * possible passwords. Also, N is hard-coded at 94.
	 * @param length of a string
	 * @return entropy in bits
	 */
	public double entropy(int length) {
		return length * logBase2(possibilities);
	}

	public static int getCharsetSize() {
		return CHARSET_SIZE;
	}
	
	public static double bruteForceTime(double entropyBits) {
		double totalPossiblePasswords = Math.pow(2,  entropyBits);
		double averageTime = (totalPossiblePasswords / 2) / attemptsPerSecond;
		return averageTime;
	}
	
	/*public static String secondsToDaysMonthsYears(double seconds) {
		String s = "";
		int second, minutes, days, months, years;
		
		if (second > 60) {
			minutes = (int)seconds / 60;
			second = seconds % 60;
			s = minutes + " minutes " + seconds + " seconds.";
		}
		return s;
	}*/
	
	public int getAttemptsPerSecond() {
		return attemptsPerSecond;
	}
	
	public void setAttemptsPerSecond(int input) {
		attemptsPerSecond = input;
	}

}
