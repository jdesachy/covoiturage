package covoiturage.calendrier;

import java.util.Calendar;

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
		Calendrier calendrier = builder.build(Calendar.getInstance());
		System.out.println(calendrier);
	}

}
