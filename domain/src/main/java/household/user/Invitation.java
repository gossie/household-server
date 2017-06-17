package household.user;

public class Invitation {

	private final Long householdId;
	
	Invitation(Long householdId) {
		this.householdId = householdId;
	}
	
	public Long getHouseholdId() {
		return householdId;
	}

}
