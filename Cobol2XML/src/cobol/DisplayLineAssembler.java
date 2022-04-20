package cobol;

import parse.*;
import parse.tokens.*;

public class DisplayLineAssembler extends Assembler {
	
	/**
	 * Pop a string, and set the DisplayLine to this string.
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
			if(t.sval().contains("Value") || t.sval().contains("value"))
			{
				c.setDisplayValue(t.sval().trim());
			}
			else if(t.sval().contains("Base")) 
			{
				c.setDisplayBase(t.sval().trim());
			}
			else if(!t.sval().equals("display"))
			{
				str = t.sval().trim() + " " + str;
			}
		}
		/*
		if(!str.isEmpty()) {
			String words[] = str.split(" ");
			String revStr = "";
			for (int i = words.length-1; i>0; i--)
			{
				revStr += words[i-1] + " ";
			}
		*/
			c.setDisplayLine(str);
			a.setTarget(c);		
	}

}
