package household.household;

import household.AbstractModel;
import household.cleaningplan.CleaningPlan;
import household.cookbook.Cookbook;
import household.foodplan.FoodPlan;
import household.shoppinglist.ShoppingList;

public class Household extends AbstractModel {
	
	private ShoppingList shoppingList;
	private CleaningPlan cleaningPlan;
	private FoodPlan foodPlan;
	private Cookbook cookbook;
	
	Household(Long id, ShoppingList shoppingList, CleaningPlan cleaningPlan, FoodPlan foodPlan, Cookbook cookbook) {
		super(id);
		this.shoppingList = shoppingList;
		this.cleaningPlan = cleaningPlan;
		this.foodPlan = foodPlan;
		this.cookbook = cookbook;
	}

	public ShoppingList getShoppingList() {
		return shoppingList;
	}

	public CleaningPlan getCleaningPlan() {
		return cleaningPlan;
	}

	public FoodPlan getFoodPlan() {
		return foodPlan;
	}

	public Cookbook getCookbook() {
		return cookbook;
	}
}
