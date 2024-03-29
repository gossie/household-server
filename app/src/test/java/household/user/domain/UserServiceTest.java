package household.user.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;

import org.junit.jupiter.api.Test;

public class UserServiceTest {

    private UserService userService;

    @Test
    public void testCreateUser() throws Exception {
        User input = new User(null, "my@email.de", "12345678");
        User expectedResult = new User("1L", "my@email.de", "12345678");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUserByEmail("my@email.de")).thenReturn(Optional.empty());
        when(userRepository.createUser(input)).thenReturn(expectedResult);
        userService = new UserService(userRepository, Collections.emptyList());

        assertThat(userService.createUser(input)).isSameAs(expectedResult);
    }

    @Test
    public void testCreateUser_duplicate() throws Exception {
        User input = new User(null, "my@email.de", "12345678");
        User existingUser = new User("1L", "my@email.de", "12345678");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUserByEmail("my@email.de")).thenReturn(Optional.of(existingUser));

        userService = new UserService(userRepository, Collections.emptyList());

        assertThatExceptionOfType(UserAlreadyExistsException.class).isThrownBy(() -> userService.createUser(input));
    }

    @Test
    public void testAcceptInvitation_noUsersLeft() throws Exception {
        User invitedUser = new User("7L", "my@email.de", "12345678");
        invitedUser.setHouseholdId("5L");
        invitedUser.addInvitation(new Invitation("3L", "9L", "sender@email.de"));

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUserById("7L")).thenReturn(invitedUser);
        when(userRepository.determineUsers("5L")).thenReturn(Collections.emptyList());

        userService = new UserService(userRepository, Collections.emptyList());
        userService.acceptInvitation("7L", "3L");

     // TDOD verify(eventBus).post(new InvitationAcceptedEvent(5L, Collections.emptyList()));
    }

    @Test
    public void testAcceptInvitation_usersLeft() throws Exception {
        User leftUser = new User("8L", "left@email.de", "12345678");
        leftUser.setHouseholdId("5L");

        User invitedUser = new User("7L", "my@email.de", "12345678");
        invitedUser.addInvitation(new Invitation("3L", "9L", "sender@email.de"));
        invitedUser.setHouseholdId("5L");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUserById("7L")).thenReturn(invitedUser);
        when(userRepository.determineUsers("5L")).thenReturn(Collections.singletonList(leftUser));

        userService = new UserService(userRepository, Collections.emptyList());
        userService.acceptInvitation("7L", "3L");

        // TDOD verify(eventBus).post(new InvitationAcceptedEvent(5L, Collections.singletonList(leftUser)));
    }

    @Test
    public void testRemoveHouldhold() throws Exception {
        UserRepository userRepository = mock(UserRepository.class);
        User user1 = new User("2L", "user1@email.de", "");
        user1.setHouseholdId("17L");
        User user2 = new User("3L", "user2@email.de", "");
        user1.setHouseholdId("18L");
        when(userRepository.determineUsers("17L")).thenReturn(Arrays.asList(user1, user2));

        userService = new UserService(userRepository, Collections.emptyList());
        userService.removeHouseholdFromUsers("17L");

        User expectedUser1 = new User("2L", "user1@email.de", "");
        User expectedUser2 = new User("3L", "user2@email.de", "");

        verify(userRepository).saveUser(expectedUser1);
        verify(userRepository).saveUser(expectedUser2);
    }

    @Test
    public void testChangePassword() throws Exception {
        User retrievedUser = new User("7L", "user@email.de", "secret");
        User savedUser = new User("7L", "user@email.de", "secretChanged");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUserById("7L")).thenReturn(retrievedUser);
        when(userRepository.saveUserAndHashPassword(retrievedUser, "secret")).thenReturn(savedUser);

        userService = new UserService(userRepository, Collections.emptyList());
        User result = userService.changePassword("7L", "secret", "secretChanged");

        assertThat(result).isSameAs(savedUser);
        assertThat(retrievedUser.getPassword()).isEqualTo("secretChanged");
    }

}
