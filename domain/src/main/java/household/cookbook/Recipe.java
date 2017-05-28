package household.cookbook;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import household.AbstractModel;

public class Recipe extends AbstractModel {

	private String name;
	private String description;
	private List<Ingredient> ingredients;
	
	Recipe(Long id, String name, String description, List<Ingredient> ingredients) {
		super(id);
		this.name = name;
		this.description = description;
		this.ingredients = new ArrayList<>(ingredients);
	}
	
	private Recipe(Long id, String name) {
		this(id, name, "", Collections.emptyList());
	}

	public Recipe minify() {
		return new Recipe(getId(), name);
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public List<Ingredient> getIngredients() {
		return Collections.unmodifiableList(ingredients);
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = new ArrayList<>(ingredients);
	}
}
