package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class MoveFromTo extends Assembler {

	/**
	 * Pop a string, and set the target MoveFromTo to this string.
	 *
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();// cobol instance created
		Token t = (Token) a.pop();// popping move to token
		if (!t.sval().isEmpty()) {
			c.setMoveTo(t.sval().trim());
		}else {
			c.setMoveTo(Double.toString(t.nval()));
		}
		t = (Token) a.pop();// token has value "to" ignore it
		t = (Token) a.pop();//move from token
		if (!t.sval().isEmpty()) {
			c.setMoveFrom(t.sval().trim());
		}else {
			c.setMoveFrom(Double.toString(t.nval()));
		}
		a.setTarget(c);
		
	}

}
