package household.cookbook.domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Recipe extends AbstractModel {

	private String name;
	private String description;
	private List<Ingredient> ingredients;
    private String url;

	public Recipe(String id, String name, String description, List<Ingredient> ingredients, String url) {
		super(id);
		this.name = name;
		this.description = description;
		this.ingredients = new ArrayList<>(ingredients);
		this.url = url;
	}

	private Recipe(String id, String name) {
		this(id, name, "", Collections.emptyList(), "");
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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
