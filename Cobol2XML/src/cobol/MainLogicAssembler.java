package cobol;

import parse.*;
import parse.tokens.*;

public class MainLogicAssembler extends Assembler {

	/**
	 * Pop a string, and set the main logic element to this string.
	 * 
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		c.setMainLogicElement(t.sval().trim());
		a.setTarget(c);
	}
	
}
