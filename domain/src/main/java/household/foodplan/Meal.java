package household.foodplan;

import java.util.Optional;

import household.AbstractModel;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@EqualsAndHashCode(callSuper=true)
@ToString(callSuper=true)
public class Meal extends AbstractModel {

	private String name;
	private Long recipeId;
	
    Meal(Long id, String name, Long recipeId) {
		super(id);
		this.name = name;
		this.recipeId = recipeId;
	}

	Meal(Long id, String name) {
		this(id, name, null);
	}
	
	public Optional<Long> getRecipeId() {
		return Optional.ofNullable(recipeId);
	}
	
	public String getName() {
		return name;
	}
	
	public void clear() {
		name = "";
		recipeId = null;
	}
}
