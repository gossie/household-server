package household.shoppinglist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingListPersistenceContext {

	@Autowired
	private ShoppingListEntityRepository shoppingListEntityRepository;
	
	@Bean
	public ShoppingListItemMapper shoppingListItemMapper() {
		return new ShoppingListItemMapper();
	}
	
	@Bean
	public ShoppingListPersistenceMapper shoppingListMapper() {
		return new ShoppingListPersistenceMapper(shoppingListItemMapper());
	}
	
	@Bean
	public ShoppingListRepository shoppingListRepository() {
		return new DefaultShoppingListRepository(shoppingListEntityRepository, shoppingListMapper());
	}
}
