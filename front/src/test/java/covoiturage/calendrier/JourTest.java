package covoiturage.calendrier;

import java.util.Calendar;

import org.junit.Test;

public class JourTest {

	@Test
	public void testToString(){
		Jour j = new Jour();
		j.setDay(Calendar.getInstance());
		System.out.println(j.toString());
	}
}
