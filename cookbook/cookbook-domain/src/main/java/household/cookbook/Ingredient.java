package household.cookbook;

public class Ingredient extends AbstractModel {

	private double amount;
	private String unit;
	private String name;

	Ingredient(String id, double amount, String unit, String name) {
		super(id);
		this.amount = amount;
		this.unit = unit;
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public String getUnit() {
		return unit;
	}

	public String getName() {
		return name;
	}
}
