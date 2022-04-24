package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import xmlwriter.XMLPayload;

public class XMLPayloadTest {

	/*
	 * This method accesses the private method addAcceptLineElement()
	 * through an accessor method getAddAcceptLineElement()
	 * it tests the method whether the addAcceptLineElement() creates rootElement object or not
	 * and returns it in the getAddAcceptLineElement() or not
	 */
	@Test
	public void testAccpetLineElement() {
		XMLPayload xmlPayload = new XMLPayload();
		assertNotNull(xmlPayload.getAcceptLineElement());
		
	}

}
