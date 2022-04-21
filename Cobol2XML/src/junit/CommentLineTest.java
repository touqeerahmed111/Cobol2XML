package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import cobol.Cobol;
import cobol.CobolParser;
import parse.Assembly;
import parse.Parser;
import parse.tokens.TokenAssembly;
import parse.tokens.Tokenizer;

public class CommentLineTest {

	@Test
	public void test() {

		//testing the comment line grammar or parser to read a pattern
		
		//starting parsers or grammar to read patterns
		Parser p = CobolParser.start();
		//converting string to tokens
		Assembly in = new TokenAssembly("***---  convert from decimal to base system ");
		//matching tokens with the parser or grammar
		Assembly out = p.bestMatch(in);
		//getting the output Cobol when assembler assembly it
		Cobol c = (Cobol) out.getTarget();
//		System.out.println(c.getCommentLine());
		//checking the has Cobol comment variable got the pattern or not
		assertFalse(c.getCommentLine().isEmpty());
		
	}

}
