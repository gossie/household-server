package household.shoppinglist.messaging;

import com.google.common.eventbus.EventBus;

import household.shoppinglist.domain.ShoppingListService;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class ShoppingListMQContext {

    @Bean(initMethod = "init")
    public ShoppingListEventHandler shoppingListEventHandler(EventBus eventBus, ShoppingListService shoppingListService) {
        return new ShoppingListEventHandler(eventBus, shoppingListService);
    }

}
