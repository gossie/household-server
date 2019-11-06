package household.cleaningplan;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
@EnableBinding(HouseholdMessageChannels.class)
class CleaningPlanEventHandler {

    private final CleaningPlanService cleaningPlanService;

    @StreamListener(HouseholdMessageChannels.PRODUCER)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        cleaningPlanService.deleteCleaningPlan(event.getCleaningPlanId());
    }

}
