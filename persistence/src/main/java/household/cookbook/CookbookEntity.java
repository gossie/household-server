package household.cookbook;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
class CookbookEntity  {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	@OneToMany(cascade=CascadeType.ALL)
	private List<RecipeEntity> recipes = new ArrayList<>();
	
	CookbookEntity minify() {
		List<RecipeEntity> minifiedRecipes = recipes.stream()
				.map(r -> r.minify())
				.collect(Collectors.toList());
		
		return new CookbookEntity(getId(), minifiedRecipes);
	}

	Optional<RecipeEntity> findRecipe(Long recipeId) {
		return recipes.stream().filter(r -> Objects.equals(recipeId, r.getId())).findFirst();
	}

	void addRecipe(RecipeEntity recipe) {
		recipes.add(recipe);
	}

	void editRecipe(Long recipeId, RecipeEntity recipe) {
		findRecipe(recipeId).ifPresent(r -> {
			r.setName(recipe.getName());
			r.setDescription(recipe.getDescription());
			r.setIngredients(recipe.getIngredients());
		});
		
	}
}