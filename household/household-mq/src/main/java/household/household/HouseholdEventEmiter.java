package household.household;

import com.google.common.eventbus.EventBus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class HouseholdEventEmiter implements HouseholdObserver {

    private final EventBus eventBus;

	@Override
	public void onHouseholdDeletion(HouseholdDeletedEvent event) {
		eventBus.post(event);
	}

}
