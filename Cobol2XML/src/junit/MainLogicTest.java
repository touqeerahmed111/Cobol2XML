package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import xmlwriter.XMLPayload;

public class MainLogicTest {

	@Test
	public void test() {
		XMLPayload xmlp = new XMLPayload();
		//Cobol File Test
		Parser p = CobolParser.start();
		Assembly input = new TokenAssembly("main-logic.");
		Assembly output = p.bestMatch(input);
		Cobol c = (Cobol) output.getTarget();
		assertTrue(c.getMainLogicElement().equals("main-logic"));
		xmlp.addElements(c);
		
		//Child Element Test
		input = new TokenAssembly("display hello world");
		output = p.bestMatch(input);
		c = (Cobol) output.getTarget();
		xmlp.addElements(c);
		
		xmlp.writeFile("mainLogicTestFile");
	}
}
