package household.foodplan;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import household.AbstractModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class FoodPlan extends AbstractModel {

	private final Map<String, Meal> meals;

	FoodPlan(Long id, Map<String, Meal> meals) {
		super(id);
		this.meals = new HashMap<>(meals);
	}
	
	public void clear() {
		meals.values().forEach(Meal::clear);
	}

	public void update(FoodPlan foodPlan) {
		foodPlan.getMeals().entrySet().stream().forEach(e -> {
		    Meal saved = meals.get(e.getKey());
		    Meal meal = new Meal(saved.getId(), e.getValue().getName());
		    meals.put(e.getKey(), meal);
		});
	}
	
	public Map<String, Meal> getMeals() {
		return Collections.unmodifiableMap(meals);
	}
}
