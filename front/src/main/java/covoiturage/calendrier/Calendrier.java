package covoiturage.calendrier;

import java.util.List;

import covoiturage.db.Tarification;

public class Calendrier {

	private List<Semaine> semaines;
	private Double amount = 0.0;

	public List<Semaine> getSemaines() {
		return semaines;
	}

	public void setSemaines(List<Semaine> semaines) {
		this.semaines = semaines;
	}

	@Override
	public String toString() {
		StringBuilder display = new StringBuilder();
		for (Semaine semaine : semaines) {
			display.append(semaine);
			display.append("\n*******");
		}
		return display.toString();
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public double getTarif(List<Tarification> tarifs, int month) {
		double res = 0.0;
		for (Semaine semaine : semaines) {
			res += semaine.getTarif(tarifs, month);
		}
		return res;
	}
}
