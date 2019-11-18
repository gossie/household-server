package household.cleaningplan;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
class CleaningPlanEventHandler {

    private final CleaningPlanService cleaningPlanService;

    @StreamListener(HouseholdMessageChannels.DELETION_PRODUCER)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        cleaningPlanService.deleteCleaningPlan(event.getCleaningPlanId());
    }

}
