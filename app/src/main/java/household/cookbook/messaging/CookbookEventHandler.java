package household.cookbook.messaging;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import household.cookbook.domain.CookbookService;
import household.household.events.HouseholdDeletedEvent;
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
        shoppingListService.deleteCookbook(event.getCookbookId());
    }

}
