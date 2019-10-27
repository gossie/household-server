package household.shoppinglist;

import com.google.common.eventbus.EventBus;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ShoppingListMQContext {

    @Bean
    public ShoppingListEventHandler shoppingListEventHandler(EventBus eventBus, ShoppingListService shoppingListService) {
        return new ShoppingListEventHandler(eventBus, shoppingListService);
    }

}
