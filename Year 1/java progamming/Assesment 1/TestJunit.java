import static org.junit.Assert.*;


import org.junit.Test;




public class testTest extends test{
	final String[] moduleNumbers = {"SOFT6018", 
			"COMH6003", "COMP6026", "SOFT6007", "MATH6000",
			"CMOD6001", "STAT6000", "SOFT6002", "SOFT6003",
	"COMP6023"};	
	
	final String[] moduleNames = {"Problem Solving&Programming 1",
			"Computer Hardware", "Networking Fundamentals 1", 
			"Web Development Fundamentals", "Essential Mathematical Skills",
			"Creativity,Innovation&Teamwork", "Statistics & Probability",
			"Introduction to HCI", "Operating Systems Fundamentals",
	"Web Publishing"};
	
	@Test
	public void testAdding() {

		boolean flag = alreadyThere (moduleNumbers,3,"SOFT6007");
		assertTrue(flag);
		assertEquals(true, flag);

	}

	@Test
	public void testAddingclasses() {

		String studentInput4 = "SOFT6018";
		boolean flag = valadation(studentInput4);
		assertTrue(flag);
		assertEquals(true, flag);
	}



	@Test
	public void testvaladation() {
		
		String studentInput4 = "SOFT6018";
		boolean flag = valadation(studentInput4);
		assertTrue(flag);
		assertEquals(true,flag);

	}

	@Test
	public void testAlreadyThere() {
		assertEquals(true, alreadyThere (moduleNumbers,3,"SOFT6007"));
		assertEquals(true, alreadyThere (moduleNumbers,4,"MATH6000"));
		assertEquals(true, alreadyThere (moduleNumbers,8,"SOFT6003"));
	}
}
