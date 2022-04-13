package cobol;

import parse.Assembler;
import parse.Assembly;
import parse.tokens.Token;

public class CommentLineAssembler extends Assembler {
	/**
	* Pop a string, and set the target DataDivision to this
	* string.
	*
	* @param Assembly the assembly to work on
	*/
	@Override
	public void workOn(Assembly a) {
		//System.out.println("commentLineAssembler");
		Cobol c = new Cobol();
		String str = "";
//		while(!a.stackIsEmpty()) {
//			Token t = (Token) a.pop();
//			str+=" "+ t.sval().trim();
//		}
		
		while(a.hasMoreElements()) {
		
			str+=" "+ a.nextElement();
			
		}
		c.setCommentLine(str);
		a.setTarget(c);
		
		
//		Token t = (Token) a.pop();
//		if(t.sval() != null) {
//		c.setCommentLine(t.sval().trim());
//		a.setTarget(c);}

	}

}
