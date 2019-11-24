package household.cookbook;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
class RecipeEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private String name;
	private String description;

	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, fetch = FetchType.EAGER)
    @Fetch(FetchMode.SUBSELECT)
	private List<IngredientEntity> ingredients = new ArrayList<>();
	private String url;

	private RecipeEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}

	RecipeEntity minify() {
		return new RecipeEntity(getId(), name);
	}

	void setName(String name) {
		this.name = name;
	}

	void setDescription(String description) {
		this.description = description;
	}

	void setIngredients(List<IngredientEntity> ingredients) {
		this.ingredients = new ArrayList<>(ingredients);
	}

    public void setUrl(String url) {
        this.url = url;
    }
}
