package covoiturage.calendrier;

import java.util.Calendar;

import covoiturage.Tarif;

public class Jour implements Comparable<Jour> {

	private Tarif aller;
	private Tarif retour;
	private Calendar day;

	public Tarif getAller() {
		return aller;
	}

	public void setAller(Tarif aller) {
		this.aller = aller;
	}

	public Tarif getRetour() {
		return retour;
	}

	public void setRetour(Tarif retour) {
		this.retour = retour;
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

	private boolean isSameMonth(int actualMonth) {
		return actualMonth == day.get(Calendar.MONTH);
	}

	private boolean isSameDay(int actualDay) {
		return actualDay == day.get(Calendar.DAY_OF_MONTH);
	}

	@Override
	public String toString() {
		String mois = String.valueOf(day.get(Calendar.MONTH) + 1);
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(day.get(Calendar.DAY_OF_MONTH));
		stringBuilder.append("/");
		stringBuilder.append(mois);
		stringBuilder.append("/");
		stringBuilder.append(day.get(Calendar.YEAR));
		return stringBuilder.toString();
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
