package household.household;

import household.HouseholdMessageChannels;
import household.user.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

@RequiredArgsConstructor
class HouseholdEventHandler implements HouseholdObserver {

    private final HouseholdService householdService;
    private final MessageChannel deletionMessageChannel;
    private final MessageChannel creationMessageChannel;

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

    @Override
    public void onHouseholdCreation(Household household) {
        Message<HouseholdCreatedEvent> message = MessageBuilder
            .withPayload(new HouseholdCreatedEvent(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId()))
            .build();

        creationMessageChannel.send(message);
    }

    @Override
    public void onHouseholdDeletion(Household household) {
        Message<HouseholdDeletedEvent> message = MessageBuilder
            .withPayload(new HouseholdDeletedEvent(household.getId(), household.getShoppingListId(), household.getCleaningPlanId(), household.getFoodPlanId(), household.getCookbookId()))
            .build();

        deletionMessageChannel.send(message);
    }
}
