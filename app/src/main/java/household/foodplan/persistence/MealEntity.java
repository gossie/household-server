package household.foodplan.persistence;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor(access = AccessLevel.PACKAGE, force = true)
@AllArgsConstructor(access = AccessLevel.PACKAGE)
class MealEntity {

	private String id;
	private String name;
	private String recipeReference;

	MealEntity(String id, String name) {
		this(id, name, null);
	}

	void clear() {
		name = "";
	}
}
