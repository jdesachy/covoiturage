package covoiturage.db;

import covoiturage.TarifName;

public class Day {

	private final String name;

	private TarifName matin;
	private TarifName soir;

	public Day(String name) {
		this.name = name;
	}

	public TarifName getMatin() {
		return matin;
	}

	public void setMatin(TarifName matin) {
		this.matin = matin;
	}

	public TarifName getSoir() {
		return soir;
	}

	public void setSoir(TarifName soir) {
		this.soir = soir;
	}

	public String getName() {
		return name;
	}
}
