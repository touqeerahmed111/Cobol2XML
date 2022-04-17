package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class CommentLineAssembler extends Assembler {
	/**
	 * Pop a string, and set the target CommentLine to this string.
	 *
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		// System.out.println("commentLineAssembler");
		Cobol c = new Cobol();//cobol instance created
		String str = "";//string to hold each stack item.
		while (!a.stackIsEmpty()) {
			Token t = (Token) a.pop();//popping each token
			str += " " + t.sval().trim();//storing onto string variable str
		}
		//if str is not empty
		if (!str.isEmpty()) {
			//breaking string and making an array of each word
			String words[] = str.split("\\s");
			String revStr = "";//store reverse string value
			//running a loop to reverse each word/
			for (int i = words.length-1; i>0; i--) {
				revStr += words[i] + " ";//storing each word to revStr variable.
			}

			c.setCommentLine(revStr);//setting a comment line field variable in cobol's instance
			a.setTarget(c);//setting a cobol instance to asymeble's target field variable
		}


	}

}
