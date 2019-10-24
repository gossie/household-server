package household.user;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ConfirmationMailService {

    private final UserService userService;

    public void sendConfirmationMail() {

    }
}