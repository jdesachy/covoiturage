package covoiturage.db;

import org.junit.Test;

public class TarifsDaoTest {

	@Test
	public void test() {
		TarifsDao dao = new TarifsDao();
		dao.delete("test");

//		dao.insert(new Tarification("PLEIN", 8.69));

//		List<Tarification> tarifs = dao.getTarifs();
//		Assert.assertEquals(1, tarifs.size());

//		Assert.assertEquals(1, dao.delete("PLEIN"));

//		tarifs = dao.getTarifs();
//		Assert.assertEquals(0, tarifs.size());

	}
}
