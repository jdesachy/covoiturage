package covoiturage.db;

public class Tarification {

	private final String name;
	private final Double amount;

	public Tarification(String name, Double amount) {
		this.name = name;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public Double getAmount() {
		return amount;
	}

	@Override
	public String toString() {
		return "Tarification [name=" + name + ", amount=" + amount + "]";
	}
}
