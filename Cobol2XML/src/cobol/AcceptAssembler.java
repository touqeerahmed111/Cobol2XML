package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class AcceptAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
//		System.out.println("AcceptAssembler");
		Cobol c = new Cobol();
		Token t =(Token) a.pop();
		if (t.sval() != null) {
			c.setAccept(t.sval().trim());
			a.setTarget(c);
		}

	}

}
