package household;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import household.cleaningplan.CleaningPlanPersistenceContext;
import household.cookbook.CookbookPersistenceContext;
import household.foodplan.FoodPlanPersistenceContext;
import household.household.HouseholdPersistenceContext;
import household.shoppinglist.ShoppingListPersistenceContext;

@Configuration
@Import({HouseholdPersistenceContext.class, CleaningPlanPersistenceContext.class, CookbookPersistenceContext.class, FoodPlanPersistenceContext.class, ShoppingListPersistenceContext.class})
public class PersistenceContext {

}
