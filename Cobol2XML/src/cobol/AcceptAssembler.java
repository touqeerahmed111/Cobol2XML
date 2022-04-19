package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class AcceptAssembler extends Assembler {

	@Override
	public void workOn(Assembly a) {
//		System.out.println("AcceptAssembler");
		Cobol c = new Cobol();		
		String str = "";
		
		while (!a.stackIsEmpty()) {
			Token t = (Token) a.pop();
			str += " " + t.sval().trim();
		}
		
		if (!str.isEmpty()) {
			String words[] = str.split("\\s");
			String revStr = "";
			
			for (int i = words.length-1; i>0; i--) {
				revStr += words[i-1] + " ";
			}
			c.setAccept(revStr);
			a.setTarget(c);
		}

	}

}
