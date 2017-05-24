package household.household;

import javax.persistence.Entity;
import javax.persistence.OneToOne;

import household.AbstractEntity;
import household.cleaningplan.CleaningPlan;
import household.cookbook.Cookbook;
import household.foodplan.FoodPlan;
import household.shoppinglist.ShoppingList;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
public class Household extends AbstractEntity {

	@OneToOne
	private ShoppingList shoppingList;
	@OneToOne
	private CleaningPlan cleaningPlan;
	@OneToOne
	private FoodPlan foodPlan;
	@OneToOne
	private Cookbook cookbook;
}
