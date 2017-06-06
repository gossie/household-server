package household.cookbook;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class IngredientDTO {

	private final double amount;
	private final String unit;
	private final String name;
}
