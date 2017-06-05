package household.user;

import household.AbstractModel;

public class User extends AbstractModel {

	private final String email;
	private String password;
	
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

}
