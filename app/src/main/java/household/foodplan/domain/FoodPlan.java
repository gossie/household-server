package household.foodplan.domain;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class FoodPlan extends AbstractModel {

    private final Map<String, Meal> meals;

    public FoodPlan(String id, Map<String, Meal> meals) {
        super(id);
        this.meals = new HashMap<>(meals);
    }

    public void clear() {
        meals.keySet().forEach(key -> meals.compute(key, (k, saved) -> new Meal(saved.getId(), "")));
    }

    public void updateMeal(String mealId, Recipe recipe, Meal meal) {
        meals.entrySet().stream()
                .filter(entry -> Objects.equals(mealId, entry.getValue().getId()))
                .map(Entry::getKey)
                .findFirst()
                .ifPresent(day -> meals.compute(day, (k, saved) -> new Meal(saved.getId(), meal.getName(), recipe)));
    }

    public Map<String, Meal> getMeals() {
        return Collections.unmodifiableMap(meals);
    }
}
