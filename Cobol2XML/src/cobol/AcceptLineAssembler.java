package cobol;

import parse.*;
import parse.tokens.*;

public class AcceptLineAssembler extends Assembler {

	/**
	 * Pop a string, and set the AcceptLine to this string.
	 * 
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		String str = "";
		while (!a.stackIsEmpty())
		{
			Token t = (Token) a.pop();
			str += " " + t.sval().trim();
		}
		if(!str.isEmpty()) {
			String words[] = str.split("\\s");
			String revStr = "";
			for (int i = words.length-1; i>0; i--)
			{
				revStr += words[i-1] + " ";
			}
			revStr = revStr.trim();
			c.setAcceptLine(revStr);
			a.setTarget(c);
		}
	}

}
