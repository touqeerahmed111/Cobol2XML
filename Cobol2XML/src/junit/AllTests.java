package junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ AssemblyTest.class, CobolTest.class, CommentLineTest.class, DisplayTest.class, GoBackTest.class,
		MainLogicTest.class, MoveFromToTest.class, NumTest.class, ParserTest.class, PerformExtensionTest.class,
		SymbolTest.class, TokenizerTest.class, TokenTest.class, XMLPayloadTest.class })
public class AllTests {

}
