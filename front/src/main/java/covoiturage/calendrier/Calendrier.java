package covoiturage.calendrier;

import java.util.List;

public class Calendrier {

	private List<Semaine> semaines;

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

}
