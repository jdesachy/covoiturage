package covoiturage.db;

import java.util.Calendar;

import org.junit.Assert;
import org.junit.Test;

import covoiturage.TarifName;
import covoiturage.calendrier.Jour;

public class DaysDaoTest {

	@Test
	public void testUpdate() throws Exception {
		Calendar cal = Calendar.getInstance();

		DaysDao dao = new DaysDao();
		Jour jour = new Jour();
		jour.setDay(cal);
		jour.setAller(TarifName.REDUC);
		jour.setRetour(TarifName.REDUC);
		dao.insert(jour);

		jour.setAller(TarifName.PLEIN);
		Assert.assertEquals(1, dao.update(jour));

		Jour newJour = dao.find(cal);
		dao.close();
		Assert.assertEquals(TarifName.PLEIN, newJour.getAller());
	}
}
