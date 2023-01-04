package household.user.persistence;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class InvitationEntityMapper {

	public Invitation map(InvitationEntity invitation) {
		return new Invitation(invitation.getId(), invitation.getHouseholdId(), invitation.getSender());
	}

	public InvitationEntity map(Invitation invitation) {
		return new InvitationEntity(invitation.getId(), invitation.getHouseholdId(), invitation.getSender());
	}

}
