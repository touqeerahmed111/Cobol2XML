package cobol;

import parse.*;
import parse.tokens.*;

public class GoBackAssembler extends Assembler {

	/**
	 * Pop a string, and set the go back element to this string.
	 * 
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		Token t = (Token) a.pop();
		c.setGoBackElement(t.sval().trim());
		a.setTarget(c);
	}

}
