package household.foodplan;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;

@Document(collection = "foodPlans")
@Data
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class FoodPlanEntity {

	@Id
	private String id;

	private Map<String, MealEntity> meals = new HashMap<>();

	FoodPlanEntity() {
	    meals.put("monday", new MealEntity());
	    meals.put("tuesday", new MealEntity());
	    meals.put("wednesday", new MealEntity());
	    meals.put("thursday", new MealEntity());
	    meals.put("friday", new MealEntity());
	    meals.put("saturday", new MealEntity());
	    meals.put("sunday", new MealEntity());
	}

	void clear() {
		meals.values().forEach(MealEntity::clear);
	}

	void update(FoodPlanEntity foodPlan) {
		foodPlan.getMeals().forEach((k, v) -> meals.put(k, v));
	}
}
