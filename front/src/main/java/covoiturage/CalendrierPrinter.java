package covoiturage;

import java.util.Calendar;

public class CalendrierPrinter {

	public static void show(String msg, Calendar c) {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append("********* ");
		stringBuilder.append(msg);
		stringBuilder.append(" | Day: ");
		stringBuilder.append(c.get(Calendar.DAY_OF_MONTH));
		stringBuilder.append(", week of year: ");
		stringBuilder.append(c.get(Calendar.WEEK_OF_YEAR));
		stringBuilder.append(", month: ");
		stringBuilder.append(c.get(Calendar.MONTH));
		stringBuilder.append(", ");
		stringBuilder.append(c.get(Calendar.YEAR));
		stringBuilder.append(" ***********");
		System.out.println(stringBuilder.toString());
	}
}
