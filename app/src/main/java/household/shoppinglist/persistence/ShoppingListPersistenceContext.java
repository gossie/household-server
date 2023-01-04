package household.shoppinglist.persistence;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShoppingListPersistenceContext {

	@Autowired
	private ShoppingListEntityRepository shoppingListEntityRepository;

	@Bean
	public ShoppingListItemEntityMapper shoppingListItemMapper() {
		return new ShoppingListItemEntityMapper();
	}

	@Bean
	public ShoppingListGroupEntityMapper shoppingListGroupMapper() {
	    return new ShoppingListGroupEntityMapper(shoppingListItemMapper());
	}

	@Bean
	public ShoppingListEntityMapper shoppingListMapper() {
		return new ShoppingListEntityMapper(shoppingListGroupMapper());
	}

	@Bean
	public ShoppingListRepository shoppingListRepository() {
		return new DefaultShoppingListRepository(shoppingListEntityRepository, shoppingListMapper());
	}
}
