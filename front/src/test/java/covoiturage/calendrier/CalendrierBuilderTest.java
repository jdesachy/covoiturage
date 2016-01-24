package covoiturage.calendrier;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import covoiturage.db.Tarification;
import covoiturage.db.TarifsDao;

public class CalendrierBuilderTest {

	private TarifsDao dao = new TarifsDao();

	@Before
	public void setup() {
		dao.insert(new Tarification("PLEIN", 8.01));
		dao.insert(new Tarification("AUCUN", 0.0));
	}

	@After
	public void after() {
		dao.delete("PLEIN");
		dao.delete("AUCUN");
	}

	@Test
	public void testBuildCalendrierFromMonth() {
		CalendrierBuilder builder = new CalendrierBuilder();
		Calendrier calendrier = builder.build(0, 2016);
		System.out.println(calendrier);
	}

	@Test
	public void testBuild() {
		CalendrierBuilder builder = new CalendrierBuilder();
		Calendrier calendrier = builder.build(1, 2016);
		System.out.println(calendrier);
	}
}
