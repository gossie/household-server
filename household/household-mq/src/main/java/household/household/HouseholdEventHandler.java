package household.household;

import household.user.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
class HouseholdEventHandler implements HouseholdObserver {

    private final HouseholdService householdService;
    private final MessageChannel messageChannel;

    public void init() {
        householdService.addObserver(this);
    }

    public void onInvitationAccepted(InvitationAcceptedEvent event) {
        event.getOldHouseholdId().ifPresent(oldHouseholdId -> {
            if(event.getLeftUsers().isEmpty()) {
                householdService.deleteHousehold(oldHouseholdId);
            }
        });
    }

    private void sendEvent(Household household) {
        Message<HouseholdDeletedEvent> message = MessageBuilder
            .withPayload(new HouseholdDeletedEvent(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId()))
            .build();

        messageChannel.send(message);
    }

    @Override
    public void onHouseholdCreation(Household household) { }

    @Override
    public void onHouseholdDeletion(Household household) {
        sendEvent(household);
    }
}
