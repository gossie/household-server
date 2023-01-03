package household.user;

import static household.user.UserAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DefaultUserRepositoryTest {

    @Test
    public void testSaveUserAndHashPassword() throws Exception {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        UserEntityRepository userEntityRepository = mock(UserEntityRepository.class);
        UserEntity userEntity = new UserEntity("5L", "test@user.de");
        userEntity.setPassword(passwordEncoder.encode("secret"));
        when(userEntityRepository.findById("5L")).thenReturn(Optional.of(userEntity));
        when(userEntityRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        DefaultUserRepository repository = new DefaultUserRepository(userEntityRepository, new UserMapper(new InvitationEntityMapper()), passwordEncoder);

        User result = repository.saveUserAndHashPassword(new User("5L", "test@user.de", "newSecret"), "secret");

        assertThat(result)
            .hasId("5L")
            .hasEmail("test@user.de")
            .hasPassword(password -> passwordEncoder.matches("newSecret", password));
    }
}
