package covoiturage.calendrier;

import org.junit.Test;

public class SemaineTest {

	@Test
	public void testNext() {
		Semaine semaine = new Semaine(52, 2015);
		System.out.println(semaine.next());
	}

	@Test
	public void testPrevious() {
		Semaine semaine = new Semaine(1, 2016);
		System.out.println(semaine.previous());
	}
}
