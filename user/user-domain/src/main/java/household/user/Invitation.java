package household.user;

import household.AbstractModel;

public class Invitation extends AbstractModel {

	private final Long householdId;
	private final String sender;
	
	Invitation(Long id, Long householdId, String sender) {
	    super(id);
		this.householdId = householdId;
		this.sender = sender;
	}
	
	public Long getHouseholdId() {
		return householdId;
	}
	
	public String getSender() {
	    return sender;
	}

}
