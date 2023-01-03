package household.household;

public class Household extends AbstractModel {
	
	private String shoppingListId;
	private String cleaningPlanId;
	private String foodPlanId;
	private String cookbookId;
	
	Household(String id, String shoppingList, String cleaningPlan, String foodPlan, String cookbook) {
		super(id);
		this.shoppingListId = shoppingList;
		this.cleaningPlanId = cleaningPlan;
		this.foodPlanId = foodPlan;
		this.cookbookId = cookbook;
	}

	public String getShoppingListId() {
		return shoppingListId;
	}

	public String getCleaningPlanId() {
		return cleaningPlanId;
	}

	public String getFoodPlanId() {
		return foodPlanId;
	}

	public String getCookbookId() {
		return cookbookId;
	}
}
