package household.cookbook;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class IngredientMapper {

	public IngredientEntity map(Ingredient ingredient) {
		return new IngredientEntity(ingredient.getId(), ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
	
	public Ingredient map(IngredientEntity ingredient) {
		return new Ingredient(ingredient.getId(), ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
}
