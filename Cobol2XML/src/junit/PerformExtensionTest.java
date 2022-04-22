package junit;

import static org.junit.Assert.*;

import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;

public class PerformExtensionTest {

	@Test
	public void test() {
		Parser p = CobolParser.start();
		Assembly input = new TokenAssembly("perform example-function thru example");
		Assembly output = p.bestMatch(input);
		Cobol c = (Cobol) output.getTarget();
		//System.out.println(c.getPerformStart() + c.getPerformEnd());
		assertFalse(c.getPerformStart().equals("") && c.getPerformEnd().equals(""));
		
	}

}
