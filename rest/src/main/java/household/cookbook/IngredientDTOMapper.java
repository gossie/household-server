package household.cookbook;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class IngredientDTOMapper {

	public Ingredient map(IngredientDTO ingredient) {
		return new Ingredient(null, ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
	
	public IngredientDTO map(Ingredient ingredient) {
		return new IngredientDTO(ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
}
