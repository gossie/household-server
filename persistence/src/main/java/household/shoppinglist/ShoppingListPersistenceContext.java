package household.shoppinglist;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
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
	
	@Bean
    public CommandLineRunner dbMigration() {
        return (args) -> {
            List<ShoppingListEntity> all = shoppingListEntityRepository.findAll().stream().map(shoppingList -> {
                List<ShoppingListGroupEntity> shoppingListGroups = shoppingList.getShoppingListGroups();
                if(shoppingListGroups.isEmpty()) {
                    ShoppingListGroupEntity groupToUse = new ShoppingListGroupEntity(null, "Global", shoppingList.getShoppingListItems());
                    shoppingList.addGroup(groupToUse);
                } else {
                    ShoppingListGroupEntity groupToUse = shoppingListGroups.get(0);
                    groupToUse.setShoppingListItems(shoppingList.getShoppingListItems());
                }
                shoppingList.clearItems();
                return shoppingList;
            }).collect(Collectors.toList());
            
            shoppingListEntityRepository.save(all);
        };
    }
}
