package household.foodplan;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
class FoodPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL)
	private Map<String, MealEntity> meals = new HashMap<>();
	
	FoodPlanEntity() {
	    id = null;
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
		foodPlan.getMeals().entrySet().stream().forEach(e -> meals.put(e.getKey(), e.getValue()));
	}
}
