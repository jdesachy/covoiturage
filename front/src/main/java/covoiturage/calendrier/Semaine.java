package covoiturage.calendrier;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class Semaine {

	private List<Jour> jours = new ArrayList<>();
	private int semaine;
	private int annee;

	public Semaine(int week, int year) {
		semaine = week;
		annee = year;
	}

	public List<Jour> getJours() {
		return jours;
	}

	public void setJours(List<Jour> jours) {
		this.jours = jours;
	}

	public int getSemaine() {
		return semaine;
	}

	public void setSemaine(int semaine) {
		this.semaine = semaine;
	}

	public boolean isCurrent() {
		int actual = Calendar.getInstance().get(Calendar.WEEK_OF_YEAR);
		return actual == semaine;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder("Semaine: ");
		stringBuilder.append(semaine);
		stringBuilder.append(", année: ");
		stringBuilder.append(annee);
		stringBuilder.append("\n");
		for (Jour jour : jours) {
			stringBuilder.append(jour);
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}

	public int getAnnee() {
		return annee;
	}

	public void setAnnee(int annee) {
		this.annee = annee;
	}

	public Semaine previous() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, semaine);
		cal.set(Calendar.YEAR, annee);

		cal.add(Calendar.WEEK_OF_YEAR, -1);
		return new Semaine(cal.get(Calendar.WEEK_OF_YEAR), cal.get(Calendar.YEAR));
	}

	public Semaine next() {
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.WEEK_OF_YEAR, semaine);
		cal.set(Calendar.YEAR, annee);

		cal.add(Calendar.WEEK_OF_YEAR, 1);
		return new Semaine(cal.get(Calendar.WEEK_OF_YEAR), cal.get(Calendar.YEAR));
	}
}
