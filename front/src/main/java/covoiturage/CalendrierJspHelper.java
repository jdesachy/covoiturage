package covoiturage;

import java.util.Calendar;

import covoiturage.calendrier.Jour;

public class CalendrierJspHelper {

	public String getId(Jour cal) {
		StringBuilder id = new StringBuilder();
		id.append(cal.getDay().get(Calendar.DAY_OF_MONTH));
		id.append("_");
		id.append(cal.getDay().get(Calendar.MONTH));
		id.append("_");
		id.append(cal.getDay().get(Calendar.YEAR));
		return id.toString();
	}
}