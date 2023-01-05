package household.user.messaging;

import com.google.common.eventbus.EventBus;

import household.user.domain.UserObserver;
import household.user.events.InvitationAcceptedEvent;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEventEmitter implements UserObserver {

	private final EventBus eventBus;

	@Override
	public void onInvitationAccepted(InvitationAcceptedEvent event) {
		eventBus.post(event);
	}

}
