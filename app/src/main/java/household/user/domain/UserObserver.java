package household.user.domain;

import household.user.events.InvitationAcceptedEvent;

public interface UserObserver {

	void onInvitationAccepted(InvitationAcceptedEvent event);

}
