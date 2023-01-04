package household.cookbook.persistence;

import java.util.ArrayList;
import java.util.List;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
class RecipeEntity {

	private String id;
	private String name;
	private String description;

	private List<IngredientEntity> ingredients = new ArrayList<>();
	private String url;

	private RecipeEntity(String id, String name) {
		this.id = id;
		this.name = name;
	}

	RecipeEntity minify() {
		return new RecipeEntity(getId(), name);
	}

	void setName(String name) {
		this.name = name;
	}

	void setDescription(String description) {
		this.description = description;
	}

	void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = new ArrayList<>(ingredients);
	}

    public void setUrl(String url) {
        this.url = url;
    }
}
