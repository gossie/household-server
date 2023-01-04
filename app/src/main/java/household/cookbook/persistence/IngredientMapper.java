package household.cookbook.persistence;

import household.cookbook.domain.Ingredient;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class IngredientMapper {

	IngredientEntity map(Ingredient ingredient) {
		return new IngredientEntity(ingredient.getId(), ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}

	Ingredient map(IngredientEntity ingredient) {
		return new Ingredient(ingredient.getId(), ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
}
