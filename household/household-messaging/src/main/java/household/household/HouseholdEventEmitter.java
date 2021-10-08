package household.household;

import com.google.common.eventbus.EventBus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class HouseholdEventEmitter implements HouseholdObserver {

    private final EventBus eventBus;

	@Override
	public void onHouseholdDeletion(HouseholdDeletedEvent event) {
		eventBus.post(event);
	}

}
