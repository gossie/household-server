package household.cleaningplan.messaging;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import household.cleaningplan.domain.CleaningPlanService;

import household.household.events.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CleaningPlanEventHandler {

    private final EventBus eventBus;
    private final CleaningPlanService cleaningPlanService;

    public void init() {
        eventBus.register(this);
    }

    @Subscribe
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        cleaningPlanService.deleteCleaningPlan(event.getCleaningPlanId());
    }

}
