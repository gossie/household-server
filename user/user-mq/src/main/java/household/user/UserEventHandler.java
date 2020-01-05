package household.user;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
class UserEventHandler implements UserServiceObserver {

    private final UserService userService;

    public void init() {
        userService.addObserver(this);
    }

    @StreamListener(HouseholdMessageChannels.DELETION_INPUT)
    public void onHouseholdDeletion(HouseholdDeletedEvent event) {
        userService.removeHouseholdFromUsers(event.getHouseholdId());
    }

    @Override
    public void onUserCreation() {
        // TODO: message werfen
    }
}
