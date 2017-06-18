package household.user;

import household.AbstractModel;

public class Invitation extends AbstractModel {

	private final Long householdId;
	
	Invitation(Long id, Long householdId) {
	    super(id);
		this.householdId = householdId;
	}
	
	public Long getHouseholdId() {
		return householdId;
	}

}
