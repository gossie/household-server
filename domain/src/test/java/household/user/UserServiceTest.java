package household.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.Optional;

import org.junit.Test;

import com.google.common.eventbus.EventBus;

public class UserServiceTest {

    @Test
    public void testCreateUser() throws Exception {
        EventBus eventBus = mock(EventBus.class);

        User input = new User(null, "my@email.de", "12345678");
        User expectedResult = new User(1L, "my@email.de", "12345678");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUser("my@email.de")).thenReturn(Optional.empty());
        when(userRepository.createUser(input)).thenReturn(expectedResult);
        UserService userService = new UserService(eventBus, userRepository);

        assertThat(userService.createUser(input)).isSameAs(expectedResult);
    }

    @Test
    public void testCreateUser_duplicate() throws Exception {
        EventBus eventBus = mock(EventBus.class);

        User input = new User(null, "my@email.de", "12345678");
        User existingUser = new User(1L, "my@email.de", "12345678");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUser("my@email.de")).thenReturn(Optional.of(existingUser));

        UserService userService = new UserService(eventBus, userRepository);

        assertThatExceptionOfType(UserAlreadyExistsException.class).isThrownBy(() -> userService.createUser(input));
    }

    @Test
    public void testAcceptInvitation_noUsersLeft() throws Exception {
        EventBus eventBus = mock(EventBus.class);

        User invitedUser = new User(7L, "my@email.de", "12345678");
        invitedUser.setHouseholdId(5L);
        invitedUser.addInvitation(new Invitation(3L, 9L, "sender@email.de"));

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUser(7L)).thenReturn(invitedUser);
        when(userRepository.determineUsers(5L)).thenReturn(Collections.emptyList());

        UserService userService = new UserService(eventBus, userRepository);
        userService.acceptInvitation(7L, 3L);

        verify(eventBus).post(new InvitationAcceptedEvent(5L));
    }

    @Test
    public void testAcceptInvitation_usersLeft() throws Exception {
        EventBus eventBus = mock(EventBus.class);

        User leftUser = new User(8L, "left@email.de", "12345678");
        leftUser.setHouseholdId(5L);

        User invitedUser = new User(7L, "my@email.de", "12345678");
        invitedUser.addInvitation(new Invitation(3L, 9L, "sender@email.de"));
        invitedUser.setHouseholdId(5L);

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUser(7L)).thenReturn(invitedUser);
        when(userRepository.determineUsers(5L)).thenReturn(Collections.singletonList(leftUser));

        UserService userService = new UserService(eventBus, userRepository);
        userService.acceptInvitation(7L, 3L);

        verifyZeroInteractions(eventBus);
    }

}
