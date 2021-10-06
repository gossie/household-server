package household.user;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.household.HouseholdDeletedEvent;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class UserEventHandler {

    private final EventBus eventBus;
    private final UserService userService;

    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void onHouseholdDeletion(HouseholdDeletedEvent event) {
        userService.removeHouseholdFromUsers(event.getHousehold().getId());
    }


}
