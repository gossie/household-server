package household.foodplan;

import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Meal extends AbstractModel {

    private final String name;
    private final Recipe recipe;

    Meal(String id, String name, Recipe recipe) {
        super(id);
        this.name = name;
        this.recipe = recipe;
    }

    Meal(String id, String name) {
        this(id, name, null);
    }

    public Optional<Recipe> getRecipe() {
        return Optional.ofNullable(recipe);
    }

    public String getName() {
        return name;
    }
}
