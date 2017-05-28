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

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class FoodPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
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
