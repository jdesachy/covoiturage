package covoiturage;

import org.junit.Test;

import covoiturage.calendrier.Jour;

public class RequestReaderTest {

	@Test
	public void testRead() {
		RequestReader reader = new RequestReader();
		Jour jour = reader.read("value=REDUC&name=tarif_4_1_2016");
		System.out.println(jour);
	}
}
