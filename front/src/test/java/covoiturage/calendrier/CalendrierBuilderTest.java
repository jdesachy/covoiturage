package covoiturage.calendrier;

import org.junit.Test;

public class CalendrierBuilderTest {

	@Test
	public void testBuild() {
		CalendrierBuilder builder = new CalendrierBuilder();
		Calendrier calendrier = builder.build(1, 2016);
		System.out.println(calendrier);
	}
}
