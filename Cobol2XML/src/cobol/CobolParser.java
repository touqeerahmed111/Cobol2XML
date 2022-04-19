/*
 * @(#)CobolParser.java	 0.1.0
 *
 * Copyright (c) 2019 Julian M. Bass
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA 02111-1307 USA
 *
 */
package cobol;

import parse.Alternation;
import parse.Empty;
import parse.Parser;
import parse.Repetition;
import parse.Sequence;
import parse.tokens.CaselessLiteral;
import parse.tokens.Literal;
import parse.tokens.Num;
import parse.tokens.QuotedString;
import parse.tokens.Symbol;
import parse.tokens.Tokenizer;
import parse.tokens.Word;

public class CobolParser {
	/**
	 * Return a parser that will recognize the selected COBOL source code constructs:
	 * 
	 * 
	 * This parser creates a <code>COBOL</code> object
	 * as an assembly's target.
	 *
	 * @return a parser that will recognize and build a 
	 *         <object>COBOL</object> from a source code file.
	 */
	public Parser cobol() {
		Alternation a = new Alternation();
		
		a.add( constantValue() );
		
		a.add( AcceptLine() );
		
		a.add( DisplayLine() );
		
		a.add( CommentLine() );
		Symbol fullstop = new Symbol('.');
		fullstop.discard();
		
		a.add( ProgramID() );
		a.add(moveFromTo());
		
		a.add( DivisionName() );
		
		a.add( SectionName() );
		
		a.add( DateWritten() );
		
		a.add(new Empty());
		
		a.add(	perform());
		return a;
	}
	
	protected Parser perform() {
		
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("perform"));
		
		Alternation start = new Alternation();
		start.add(new Word());
		s.add(start);
		s.add(new Word());
		
		Alternation end = new Alternation();
		end.add(new Word());
		s.add(end);
		
		// work on the sequence.
		s.setAssembler(new PerformAssembler());
		return s;
	}

	/*
	* Return a parser that will recognize the grammar:
	*
	* move something to somewhere 
	*
	*/
	protected Parser moveFromTo() {
	
	Sequence s = new Sequence();
	s.add(new CaselessLiteral("move"));
	Alternation from = new Alternation();//read if a token is number or word
	from.add(new Word());
	from.add(new Num());
	s.add(from);//adding alternation to sequence
	s.add(new Word());
	Alternation to = new Alternation();//read if a token is number or word
	to.add(new Word());
	to.add(new Num());
	s.add(to);//adding alternation to sequence
	// work on the sequence.
	s.setAssembler(new MoveFromToAssembler());
	return s;
	}
	
	/*
	* Return a parser that will recognise the grammar:
	*
	* accept
	*
	*/
	protected Parser AcceptLine() {
		
	Sequence s = new Sequence();
	s.add(new CaselessLiteral("accept"));
	s.add(new Repetition(new Word()));
	s.setAssembler(new AcceptLineAssembler());
	return s;
	}
	
	/*
	* Return a parser that will recognize the grammar:
	*
	* ***--- comment text
	*
	*/
	protected Parser CommentLine() {
	//System.out.println("commentLine()");
	Sequence s = new Sequence();
	s.add(new Symbol("*").discard());
	s.add(new Symbol("*").discard());
	s.add(new Symbol("*").discard());
	s.add(new Symbol("-").discard());
	s.add(new Symbol("-").discard());
	s.add(new Symbol("-").discard());
	s.add(new Repetition(new Word()));//repeat the words
	// work on the sequence.
	s.setAssembler(new CommentLineAssembler());
	return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 * display 
	 * 
	 */
	protected Parser DisplayLine()
	{
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("display"));
		s.add(new QuotedString());
		s.add(new Word());
		Alternation alt = new Alternation();
		alt.add(new QuotedString());
		alt.add(new Word());
		s.setAssembler(new DisplayLineAssembler());
		return s;
	}
	
	/*
	* Return a parser that will recognize the grammar:
	*
	* <line number> <contstant name> "value" <constant value>.
	*
	*/
	protected Parser constantValue() {
	//System.out.println("constantValue()");
	Sequence s = new Sequence();
	s.add(new Num() );
	s.add(new Word() );
	s.add(new CaselessLiteral("value") );
	s.add(new Num() );
	s.setAssembler(new ConstantValueAssembler());
	return s;
	}
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Program Identifier = Word
	 *
	 */
	protected Parser ProgramID() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("program-id") );
		s.add(new Symbol('.').discard());	
		s.add(new Word().setAssembler(new Program_idAssembler()));
		return s;
	}

	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    <divisionName> division
	 *
	 */
	protected Parser DivisionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new DivisionAssembler()));
		s.add(new CaselessLiteral("division") );
		s.add(new Symbol('.').discard());
		return s;
	}
	
	/*
	 * Return a parser that will recognize the grammar:
	 * 
	 *    Section Name = Word
	 *
	 */
	protected Parser SectionName() {
		Sequence s = new Sequence();
		s.add(new Word().setAssembler(new SectionNameAssembler()));
		s.add(new CaselessLiteral("section") );
		s.add(new Symbol('.').discard());	

		return s;
	}
	
	/*
	 * Return a parser that will recognise the grammar:
	 * 
	 *    working-storage section
	 *
	 */
	protected Parser DateWritten() {
		Sequence s = new Sequence();
		s.add(new CaselessLiteral("date-written") );
		s.add(new Symbol('.').discard());
		s.add(new Num());
		s.add(new Symbol('-').discard());

		//This next Word actually contains month and year (which are extracted in DateAssembler
		s.add(new Word());
		s.add(new Symbol('-').discard());
		s.add(new Word().discard());
		s.add(new Symbol('.').discard());
		s.setAssembler(new DateAssembler());
		return s;
	}


	/**
	 * Return the primary parser for this class -- cobol().
	 *
	 * @return the primary parser for this class -- cobol()
	 */
	public static Parser start() {
		return new CobolParser().cobol();
	}

	/**
	 * Returns a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol's grammar.
	 *
	 * @return a tokenizer that does not allow spaces to appear inside
	 * the "words" that identify cobol grammar.
	 */
	public static Tokenizer tokenizer() {
		Tokenizer t = new Tokenizer();
		t.wordState().setWordChars(' ', ' ', false);
		return t;
	}

}
