package household.shoppinglist;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class ShoppingListEventHandler {

    private final EventBus eventBus;
    private final ShoppingListService shoppingListService;

    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        shoppingListService.deleteShoppingList(event.getShoppingListId());
    }

}