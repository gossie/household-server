package household.household;

public class Household extends AbstractModel {
	
	private Long shoppingListId;
	private Long cleaningPlanId;
	private Long foodPlanId;
	private Long cookbookId;
	
	Household(Long id, Long shoppingList, Long cleaningPlan, Long foodPlan, Long cookbook) {
		super(id);
		this.shoppingListId = shoppingList;
		this.cleaningPlanId = cleaningPlan;
		this.foodPlanId = foodPlan;
		this.cookbookId = cookbook;
	}

	public Long getShoppingListId() {
		return shoppingListId;
	}

	public Long getCleaningPlanId() {
		return cleaningPlanId;
	}

	public Long getFoodPlanId() {
		return foodPlanId;
	}

	public Long getCookbookId() {
		return cookbookId;
	}
}
