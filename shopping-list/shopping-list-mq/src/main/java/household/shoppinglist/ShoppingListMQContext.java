package household.shoppinglist;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ShoppingListMQContext {

    @Bean
    public ShoppingListEventHandler shoppingListEventHandler(ShoppingListService shoppingListService) {
        return new ShoppingListEventHandler(shoppingListService);
    }

}
