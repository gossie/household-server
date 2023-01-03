package household.user;

import household.AbstractModel;

public class Invitation extends AbstractModel {

	private final String householdId;
	private final String sender;
	
	Invitation(String id, String householdId, String sender) {
	    super(id);
		this.householdId = householdId;
		this.sender = sender;
	}
	
	public String getHouseholdId() {
		return householdId;
	}
	
	public String getSender() {
	    return sender;
	}

}
