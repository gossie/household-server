package household.cookbook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookbookPersistenceContext {

	@Autowired
	private CookbookEntityRepository cookbookEntityRepository;
	
	@Bean
	public IngredientMapper ingredientMapper() {
		return new IngredientMapper();
	}

	@Bean
	public RecipeMapper recipeMapper() {
		return new RecipeMapper(ingredientMapper());
	}
	
	@Bean
	public CookbookMapper cookbookMapper() {
		return new CookbookMapper(recipeMapper());
	}
	
	@Bean
	public CookbookRepository cookbookRepository() {
		return new DefaultCookbookRepository(cookbookEntityRepository, cookbookMapper());
	}
}
