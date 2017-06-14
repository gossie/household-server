package household.user;

import household.AbstractModel;

public class User extends AbstractModel {

	private final String email;
	private String password;
	private Long householdId;
	
	User(Long id, String email, String password) {
		super(id);
		this.email = email;
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Long getHouseholdId() {
		return householdId;
	}
	
	public void setHouseholdId(Long householdId) {
		this.householdId = householdId;
	}
}
