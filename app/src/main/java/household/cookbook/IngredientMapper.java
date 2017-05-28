package household.cookbook;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class IngredientMapper {

	public IngredientEntity map(IngredientDTO ingredient) {
		return new IngredientEntity(ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
	
	public IngredientDTO map(IngredientEntity ingredient) {
		return new IngredientDTO(ingredient.getAmount(), ingredient.getUnit(), ingredient.getName());
	}
}
