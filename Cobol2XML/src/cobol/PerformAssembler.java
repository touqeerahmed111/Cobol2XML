package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class PerformAssembler extends Assembler {

	/**
	 * Pop a string, and set the target perform to this string.
	 *
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();// cobol instance created
		Token t = (Token) a.pop();// popping first token

		/**
		while (!a.stackIsEmpty()) {
			t = (Token) a.pop(); // Pops the next token
			System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + t.value());
			
		}
		**/
		
		// checks for action word or location
	    //System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + t.value());
		//System.out.println("First line --------------------");
		
		if (t.sval().trim() == "until" || t.sval().trim() == "test") {
			// pops the action to the string
			String actionString = t.sval().trim();
			while (!a.stackIsEmpty()) {
				t = (Token) a.pop(); // Pops the next token
				actionString += " " + t.sval().trim();
			}
			// if the action string isn't empty, reverse the string
			if (!actionString.isEmpty()) {
				// adds each word to the array
				String words[] = actionString.split("\\s");
				String reversedString = "";

				// runs through the words array
				for (int i = words.length - 1; i > 0; i--) {
					reversedString += words[i] + " "; // adds each word to the string
				}

				c.setPerformAction(reversedString);
				a.setTarget(c);
			}

		} else {
			c.setPerformEnd(t.sval().trim());

			t = (Token) a.pop();// token has value "thru" and is popped to be removed
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + t.sval().trim());

			t = (Token) a.pop();// token to indicate starting point
			//System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>> " + t.sval().trim());

			c.setPerformStart(t.sval().trim());

			a.setTarget(c);
		}

	}
	

}
