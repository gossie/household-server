package household.household.messaging;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import household.household.domain.HouseholdService;
import household.user.events.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class HouseholdEventHandler {

    private final EventBus eventBus;
    private final HouseholdService householdService;

    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void onInvitationAccepted(InvitationAcceptedEvent event) {
        event.getOldHouseholdId().ifPresent(oldHouseholdId -> {
            if(event.getLeftUserIds().isEmpty()) {
                householdService.deleteHousehold(oldHouseholdId);
            }
        });
    }
}
