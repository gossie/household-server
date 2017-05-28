package household.household;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import household.AbstractEntity;
import household.cleaningplan.CleaningPlanEntity;
import household.cookbook.CookbookEntity;
import household.foodplan.FoodPlanEntity;
import household.shoppinglist.ShoppingListEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
public class HouseholdEntity extends AbstractEntity {

	@OneToOne
	private ShoppingListEntity shoppingList;
	@OneToOne
	private CleaningPlanEntity cleaningPlan;
	@OneToOne
	private FoodPlanEntity foodPlan;
	@OneToOne
	private CookbookEntity cookbook;
}
