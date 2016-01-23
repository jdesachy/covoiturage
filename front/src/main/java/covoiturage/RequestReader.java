package covoiturage;

import java.util.Calendar;
import java.util.regex.Pattern;

import covoiturage.calendrier.Jour;

public class RequestReader {

	private final Pattern request_separator = Pattern.compile("&");
	private final Pattern equals = Pattern.compile("=");
	private final Pattern day_separator = Pattern.compile("_");

	public Jour read(String request) {
		String[] params = request_separator.split(request);
		String value = equals.split(params[0])[1];
		String name = equals.split(params[1])[1];

		String dayString = name.replaceFirst("tarif_", "");
		Jour jour = new Jour();
		jour.setDay(buildCal(dayString));
		jour.setAller(TarifName.valueOf(value));
		jour.setRetour(TarifName.valueOf(value));
		return jour;
	}

	private Calendar buildCal(String dayString) {
		String[] datas = day_separator.split(dayString);
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.DAY_OF_MONTH, Integer.parseInt(datas[0]));
		cal.set(Calendar.MONTH, Integer.parseInt(datas[1]));
		cal.set(Calendar.YEAR, Integer.parseInt(datas[2]));
		return cal;
	}
}
