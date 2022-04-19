package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class CobolTest {

	@Test
	public void test() {
//		fail("Not yet implemented");
		
		//comparing 2 Cobol objects
		Cobol c1 = new Cobol();
		Cobol c2 = new Cobol();
		assertTrue(Cobol.ifCobolObjEqual(c1, c2));
		
		//comparing Cobol c1 object with assembly's target object
		Tokenizer t = CobolParser.tokenizer();
		Parser p = CobolParser.start();
		t.setString("Cobol. test.");
		Assembly in = new TokenAssembly(t);
		Assembly out = p.bestMatch(in);
		out.setTarget(c1);
		assertTrue(c2.equals(out.getTarget()));
	}

}
