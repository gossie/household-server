package household.household.messaging;

import com.google.common.eventbus.EventBus;

import household.household.domain.HouseholdObserver;
import household.household.events.HouseholdDeletedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseholdEventEmitter implements HouseholdObserver {

    private final EventBus eventBus;

	@Override
	public void onHouseholdDeletion(HouseholdDeletedEvent event) {
		eventBus.post(event);
	}

}
