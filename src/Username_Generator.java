import java.util.Scanner;
import java.util.Random;

public class Username_Generator {
	
	public static void main(String[]arg) {
		Scanner scan = new Scanner(System.in);
		Random randGen = new Random();
		
		System.out.println("Welcome to Username Generator!");
		System.out.print("Please enter your first name: ");
		String firstName = scan.nextLine();
		System.out.print("Please enter your last name: ");
		String lastName = scan.nextLine();
		
	//Part one: Get a Valid First and Last Name 
		
		//initialize firstNameChar
		char firstNameChar = 'n';
		//assigns firstNameChar in lower case IFF firstName is not empty
		if (firstName.length() > 0) {
			firstNameChar = Character.toLowerCase(firstName.charAt(0));	
		}
		
		//Validates the first name to be at least one character long and 
		//must start with a letter	
		while ((firstName.length() == 0) || (!Character.isLetter(firstNameChar))) {
			System.out.print("Please enter a valid first name (at least one letter): ");
			firstName = scan.nextLine();
			//assigns firstNameChar in lower case IFF firstName is not empty
			if (firstName.length() > 0) {
				firstNameChar = Character.toLowerCase(firstName.charAt(0));
			}
		}
		
		//use a loop to validate the last name: it must not contain 
		//any spaces
		int blankSpace = -1;
		while ((lastName.length() == 0) || (blankSpace == -1)) {
			for (int i=0; i<lastName.length(); ++i) {
				if (Character.isWhitespace(lastName.charAt(i))) {
					blankSpace += 1;
				}
			}
			//If no blank spaces, adds one so blankSpace == 0
			blankSpace += 1;
			
			//last name must not be empty (blank)
			while (blankSpace > 0 || (lastName.length() == 0)) {
				System.out.print("The last name is invalid. "
						+ "Please re-enter without blank spaces: ");
				lastName = scan.nextLine();
				blankSpace = -1;
			}
		}
		
	//Part Two: Generate a Username
		
		//generate a two-digit random number between 10 and 99, inclusive)
		int randNum = randGen.nextInt(90) + 10;
		
		String lastNameLowerCase = "";
		String username = "";
		
		//the first five characters of their last name in lower case; 
		for (int i=0; i < lastName.length(); ++i) {
			char ch1 = Character.toLowerCase(lastName.charAt(i));
			lastNameLowerCase += ch1;
		}
		//create a username that is the first letter of their first name in lower case
		//if the name is shorter than five characters, use the whole last name
		//the two-digit number
		if (lastName.length() > 4) {
			username = firstNameChar + lastNameLowerCase.substring(0, 5) + randNum;
		}
		else {
			username = firstNameChar + lastNameLowerCase + randNum;
		}
		
		System.out.println("Your Random Username is: " + username);
	}

}
