package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;

public class DisplayTest {

	@Test
	public void test() {
		Parser p = CobolParser.start();
		//Display Value Test
		Assembly in = new TokenAssembly("display \" value \" yes");
		Assembly output = p.bestMatch(in);
		Cobol c = (Cobol) output.getTarget();
		assertTrue(c.getDisplayValue().equals("yes"));
		assertFalse(c.getDisplayValue().equals(" \" value \" yes"));
		
		//Display Base Test
		in = new TokenAssembly("display \" Base \" no");
		output = p.bestMatch(in);
		c = (Cobol) output.getTarget();
		assertTrue(c.getDisplayBase().equals("no"));
		assertFalse(c.getDisplayBase().equals(" \" Base  \" no"));
		
		//Display Line Test
		in = new TokenAssembly("display hello world");
		output = p.bestMatch(in);
		c = (Cobol) output.getTarget();
		assertTrue(c.getDisplayLine().equals("hello world"));
		assertFalse(c.getDisplayLine().equals("hell0 w0rld"));
		
	}

}
