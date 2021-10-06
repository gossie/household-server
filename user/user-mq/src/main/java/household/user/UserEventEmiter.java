package household.user;

import com.google.common.eventbus.EventBus;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserEventEmiter implements UserObserver {

	private final EventBus eventBus;
	
	@Override
	public void onInvitationAccepted(InvitationAcceptedEvent event) {
		eventBus.post(event);
	}

}
