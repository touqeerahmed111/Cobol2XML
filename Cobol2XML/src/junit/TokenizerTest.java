package junit;

import parse.tokens.Token;
import parse.tokens.Tokenizer;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.Vector;

import org.junit.Test;

import cobol.CobolParser;

public class TokenizerTest {

	@Test
	public void test() {
		Tokenizer t = CobolParser.tokenizer();
		t.setString("Hello world 2022");
		Token tok = null;
		try {
			tok = t.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(tok.sval().equals("Hello"));
		try {
			tok = t.nextToken();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		assertFalse(tok.sval().equals("World"));
		try {
			tok = t.nextToken();
		} catch (IOException e) {
			e.printStackTrace();
		}
		assertTrue(tok.nval()==2022);
	}
}
