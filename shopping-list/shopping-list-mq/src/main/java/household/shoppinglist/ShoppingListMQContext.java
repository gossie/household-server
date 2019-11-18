package household.shoppinglist;

import household.HouseholdMessageChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@EnableBinding(HouseholdMessageChannels.class)
class ShoppingListMQContext {

    @Bean
    public ShoppingListEventHandler shoppingListEventHandler(ShoppingListService shoppingListService) {
        return new ShoppingListEventHandler(shoppingListService);
    }

}
