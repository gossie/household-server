package household.cookbook;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CookbookEventHandler {

    private final EventBus eventBus;
    private final CookbookService shoppingListService;

    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        shoppingListService.deleteCookbook(event.getHousehold().getShoppingListId());
    }

}
