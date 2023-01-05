package household.foodplan.persistence;

import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "foodPlans")
@Data
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
class FoodPlanEntity {

	@Id
	private String id;

	private Map<String, MealEntity> meals = new HashMap<>();

	void clear() {
		meals.values().forEach(MealEntity::clear);
	}

	void update(FoodPlanEntity foodPlan) {
		foodPlan.getMeals().forEach((k, v) -> meals.put(k, v));
	}
}
