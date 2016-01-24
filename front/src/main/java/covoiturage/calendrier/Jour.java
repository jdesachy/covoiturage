package covoiturage.calendrier;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import covoiturage.db.Tarification;

public class Jour implements Comparable<Jour> {

	private String tarif;
	private Calendar day;

	public String getAller() {
		return tarif;
	}

	public void setAller(String tarif) {
		this.tarif = tarif;
	}

	public Calendar getDay() {
		return day;
	}

	public void setDay(Calendar day) {
		this.day = day;
	}

	public boolean isCurrent() {
		Calendar now = Calendar.getInstance();
		int actualDay = now.get(Calendar.DAY_OF_MONTH);
		int actualMonth = now.get(Calendar.MONTH);
		return isSameDay(actualDay) && isSameMonth(actualMonth);
	}

	public boolean isActif(int month) {
		return isSameMonth(month);
	}

	private boolean isSameMonth(int actualMonth) {
		return actualMonth == day.get(Calendar.MONTH);
	}

	private boolean isSameDay(int actualDay) {
		return actualDay == day.get(Calendar.DAY_OF_MONTH);
	}

	public double getTarif(List<Tarification> tarifs) {
		double res = 0.0;
		for (Tarification tarification : tarifs) {
			if (tarification.getName().equals(this.tarif)) {
				res = tarification.getAmount();
			}
		}
		return res;
	}

	private SimpleDateFormat format = new SimpleDateFormat("EEEE dd MMM yyyy", Locale.FRANCE);

	@Override
	public String toString() {
		return format.format(day.getTime());
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((day == null) ? 0 : day.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Jour other = (Jour) obj;
		if (day == null) {
			if (other.day != null)
				return false;
		} else if (!day.equals(other.day))
			return false;
		return true;
	}

	@Override
	public int compareTo(Jour o) {
		return this.day.compareTo(o.getDay());
	}
}
