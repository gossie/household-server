package household.user;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
@EnableBinding(HouseholdMessageChannels.class)
class UserEventHandler {

    private final UserService userService;

    @StreamListener(HouseholdMessageChannels.PRODUCER)
    public void onHouseholdDeletion(HouseholdDeletedEvent event) {
        userService.removeHouseholdFromUsers(event.getHouseholdId());
    }

}
