package household.user.domain;

public class Invitation extends AbstractModel {

	private final String householdId;
	private final String sender;

	public Invitation(String id, String householdId, String sender) {
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
