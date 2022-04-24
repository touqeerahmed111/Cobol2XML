package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;

public class MoveFromToTest {
	// testing the move from to grammar or parser to read a pattern
	@Test
	public void test() {
		// starting parsers or grammar to read patterns
		Parser p = CobolParser.start();
		// converting string to tokens
		Assembly in = new TokenAssembly("move entry_number  to w_number");
		// matching tokens with the parser or grammar
		Assembly out = p.bestMatch(in);
		// getting the output Cobol when assembler assembly it
		Cobol c = (Cobol) out.getTarget();
//		System.out.println(c.getMoveFrom());
		// checking the has Cobol moveFrom variable got the pattern or not
		assertTrue(c.getMoveFrom().isEmpty());

		// checking the has Cobol moveFrom variable got the pattern or not
		assertTrue(c.getMoveTo().isEmpty());
	}

}
