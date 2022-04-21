package junit;

import static org.junit.Assert.*;

import org.junit.Test;

import xmlwriter.XMLPayload;

public class XMLPayloadTest {

	@Test
	public void testAccpetLineElement() {
		XMLPayload xmlPayload = new XMLPayload();
		assertNotNull(xmlPayload.getAcceptLineElement());
		
	}

}
