package household.household.domain;

import household.household.events.HouseholdDeletedEvent;

public interface HouseholdObserver {

	void onHouseholdDeletion(HouseholdDeletedEvent event);

}
