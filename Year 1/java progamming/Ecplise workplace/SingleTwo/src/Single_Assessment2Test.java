import static org.junit.Assert.*;

import org.junit.Test;


public class Single_Assessment2Test extends Single_Assessment2{

	
	@Test
	public void testAddingStudent() {
		// testing student if there where there or not :p
		String choices = "R00102439";
		int flag = findIndex(choices);
		assertEquals(-1,flag);
		
	}

	@Test
	public void testAddingModule() {
		// testing module if there where there or not :p
				String choices = "R00102430";
				int flag = findmodule(choices);
				assertEquals(-1,flag);
				
	}
	@Test
	public void testFindIndex() {
				String choices = "R00102439";
				int flag = findIndex(choices);
				assertEquals(-1,flag);
				
	}

}
