package household.cookbook;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class RecipeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	private String name;
	private String description;
	@OneToMany(cascade=CascadeType.ALL)
	private List<IngredientEntity> ingredients = new ArrayList<>();
	
	private RecipeEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	public RecipeEntity minify() {
		return new RecipeEntity(getId(), name);
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = new ArrayList<>(ingredients);
	}
}
