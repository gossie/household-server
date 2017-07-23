package household.user;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.Test;

public class UserServiceTest {

    @Test
    public void testCreateUser() throws Exception {
        User input = new User(null, "my@email.de", "12345678");
        User expectedResult = new User(1L, "my@email.de", "12345678");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUser("my@email.de")).thenReturn(Optional.empty());
        when(userRepository.createUser(input)).thenReturn(expectedResult);
        UserService userService = new UserService(userRepository);

        assertThat(userService.createUser(input)).isSameAs(expectedResult);
    }

    @Test
    public void testCreateUser_duplicate() throws Exception {
        User input = new User(null, "my@email.de", "12345678");
        User existingUser = new User(1L, "my@email.de", "12345678");

        UserRepository userRepository = mock(UserRepository.class);
        when(userRepository.determineUser("my@email.de")).thenReturn(Optional.of(existingUser));
        UserService userService = new UserService(userRepository);

        assertThatExceptionOfType(UserAlreadyExistsException.class).isThrownBy(() -> userService.createUser(input));
    }

}
