package household.user;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import household.AbstractModel;

public class User extends AbstractModel {

	private final String email;
	private String password;
	private Long householdId;
	private final List<Invitation> invitations;
	
	User(Long id, String email, String password) {
		super(id);
		this.email = email;
		this.password = password;
		this.invitations = new ArrayList<>();
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
	
	public List<Invitation> getInvitations() {
		return Collections.unmodifiableList(invitations);
	}

	public void addInvitation(Invitation invitation) {
		invitations.add(invitation);
	}

    public void acceptInvitation(Long invitationId) {
        invitations.stream()
            .filter(invitation -> invitationId.equals(invitation.getId()))
            .findFirst()
            .ifPresent(invitation -> {
                householdId = invitation.getHouseholdId();
                invitations.removeIf(i -> invitationId.equals(i.getId()));
            });
    }

	public void rejectInvitation(Long invitationId) {
		invitations.removeIf(invitation -> invitationId.equals(invitation.getId()));
	}
}
