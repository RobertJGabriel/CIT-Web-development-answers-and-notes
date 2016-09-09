import static org.junit.Assert.*;

import org.junit.Test;
import javax.mail.MessagingException;
import javax.mail.AuthenticationFailedException;
import org.junit.Test;

public class Email_ReminderTest extends Email_Reminder {

	
	/*
	 * @Test (expected=MessageException.class)
	 * we want the one below to fail.
	 */


	@Test (expected=AuthenticationFailedException.class)
	public void testEmailAlert() {
		fail("Not yet implemented");
	}

	public void  testAlertSystem(){
		
		int countDownTime  = readTime();		
		assertEquals(1,countDownTime );

	}
	
}
