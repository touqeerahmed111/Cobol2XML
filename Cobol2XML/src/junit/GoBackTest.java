package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import xmlwriter.XMLPayload;

public class GoBackTest {

	@Test
	public void test() {
		//Cobol File Test
		Parser parser = CobolParser.start();
		Assembly input = new TokenAssembly("goback.");
		Assembly output = parser.bestMatch(input);
		Cobol cobol = (Cobol) output.getTarget();
		assertTrue(cobol.getGoBackElement().equals("goback"));
		
		//Child Element Test
		XMLPayload xmlp = new XMLPayload();
		
		Parser p = CobolParser.start();
		Assembly in = new TokenAssembly("main-logic.");
		Assembly out = p.bestMatch(in);
		Cobol c = (Cobol) out.getTarget();
		xmlp.addElements(c);
		
		//Child Element Test
		in = new TokenAssembly("display hello world");
		out = p.bestMatch(in);
		c = (Cobol) out.getTarget();
		xmlp.addElements(c);
		
		in = new TokenAssembly("goback.");
		out = p.bestMatch(input);
		c = (Cobol) out.getTarget();
		xmlp.addElements(c);
		
		in = new TokenAssembly("display hello galaxy");
		out = p.bestMatch(in);
		c = (Cobol) out.getTarget();
		xmlp.addElements(c);
		
		xmlp.writeFile("goBackTestFile");
	}

}
