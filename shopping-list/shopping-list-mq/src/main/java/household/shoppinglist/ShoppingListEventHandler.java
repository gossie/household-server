package household.shoppinglist;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
@EnableBinding(HouseholdMessageChannels.class)
class ShoppingListEventHandler {

    private final ShoppingListService shoppingListService;

    @StreamListener(HouseholdMessageChannels.DELETION_PRODUCER)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        shoppingListService.deleteShoppingList(event.getShoppingListId());
    }

}
