package cobol;

import parse.*;
import parse.tokens.*;

public class DisplayAssembler extends Assembler {
	
	/**
	 * Pop a string, and set the DisplayLine to this string.
	 * 
	 * @param Assembly the assembly to work on
	 */
	@Override
	public void workOn(Assembly a) {
		Cobol c = new Cobol();
		int index = 0;
		String other = "";
		String str = "";
		while (!a.stackIsEmpty())
		{
			Token t = (Token) a.pop();
			if(t.sval().contains("Value") || t.sval().contains("value"))
			{
				other = other.trim();
				c.setDisplayValue(other);
				other = "";
				index += 1;
			}
			else if(t.sval().contains("Base")) 
			{
				other = other.trim();
				c.setDisplayBase(other);
				other = "";
				index += 1;
			}
			else if(!t.sval().equals("display"))
			{
				other = t.sval().trim() + " " + other;
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
		if(index == 0)
			str = other;
		if(!str.equals(""))
		{
			str = str.trim();
			c.setDisplayLine(str);
		}
		a.setTarget(c);		
	}

}
