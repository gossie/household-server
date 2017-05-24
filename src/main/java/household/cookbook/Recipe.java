package household.cookbook;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class Recipe extends AbstractEntity {

	private String name;
	private String description;
	@OneToMany(cascade=CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<>();
	
	private Recipe(Long id, String name) {
		super(id);
		this.name = name;
	}

	public Recipe minify() {
		return new Recipe(getId(), name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = new ArrayList<>(ingredients);
	}
}
