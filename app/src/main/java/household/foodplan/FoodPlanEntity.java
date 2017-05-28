package household.foodplan;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.CascadeType;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class FoodPlanEntity extends AbstractEntity {

	@ElementCollection
	@OneToMany(cascade=CascadeType.ALL)
	private Map<String, MealEntity> meals = new HashMap<>();

	public void clear() {
		meals.values().forEach(MealEntity::clear);
	}

	public void update(FoodPlanEntity foodPlan) {
		foodPlan.getMeals().entrySet().stream().forEach(e -> meals.put(e.getKey(), e.getValue()));
	}
}
