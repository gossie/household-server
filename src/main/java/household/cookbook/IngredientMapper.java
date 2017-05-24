package household.cookbook;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class IngredientMapper {

	public Ingredient map(IngredientDTO ingredient) {
		return new Ingredient(ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
	
	public IngredientDTO map(Ingredient ingredient) {
		return new IngredientDTO(ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
}
