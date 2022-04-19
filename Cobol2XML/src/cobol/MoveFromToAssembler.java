package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class MoveFromToAssembler extends Assembler {
	/**
	 * Pop a string, and set the target MoveFromTo to this string.
	 *
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();// cobol instance created
		Token t = (Token) a.pop();// popping perform start token
		if (!t.sval().isEmpty()) {
			c.setMoveTo(t.sval().trim());
		}else {
			c.setMoveTo(Double.toString(t.nval()));
		}
		t = (Token) a.pop();// token has value "thru" ignore it
		t = (Token) a.pop();//perform end token
		if (!t.sval().isEmpty()) {
			c.setMoveFrom(t.sval().trim());
		}else {
			c.setMoveFrom(Double.toString(t.nval()));
		}
		a.setTarget(c);
		
	}

	
}
