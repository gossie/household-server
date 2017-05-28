package household.household;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import household.cleaningplan.CleaningPlanEntity;
import household.cookbook.CookbookEntity;
import household.foodplan.FoodPlanEntity;
import household.shoppinglist.ShoppingListEntity;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
public class HouseholdEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	@OneToOne
	private ShoppingListEntity shoppingList;
	@OneToOne
	private CleaningPlanEntity cleaningPlan;
	@OneToOne
	private FoodPlanEntity foodPlan;
	@OneToOne
	private CookbookEntity cookbook;
}
