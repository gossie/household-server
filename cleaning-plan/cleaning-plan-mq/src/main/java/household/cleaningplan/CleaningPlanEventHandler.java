package household.cleaningplan;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
class CleaningPlanEventHandler {

    private final CleaningPlanService cleaningPlanService;

    @StreamListener(HouseholdMessageChannels.DELETION_INPUT)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        cleaningPlanService.deleteCleaningPlan(event.getCleaningPlanId());
    }

    @StreamListener(HouseholdMessageChannels.CREATION_INPUT)
    public void onHouseholdCreation(HouseholdCreatedEvent event) {
        System.out.println("cleaning-plam-service: noticed that household with id [" + event.getHouseholdId() + "] was created. But I don't care!");
    }

}
