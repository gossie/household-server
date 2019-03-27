package household.user;

import static household.user.UserAssert.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class DefaultUserRepositoryTest {

    @Test
    public void testSaveUserAndHashPassword() throws Exception {
        UserEntityRepository userEntityRepository = mock(UserEntityRepository.class);
        when(userEntityRepository.save(any(UserEntity.class))).thenAnswer(invocation -> invocation.getArguments()[0]);

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        DefaultUserRepository repository = new DefaultUserRepository(userEntityRepository, new UserMapper(new InvitationEntityMapper()), passwordEncoder);

        User result = repository.saveUserAndHashPassword(new User(5L, "test@user.de", "secret"));

        assertThat(result)
            .hasId(5L)
            .hasEmail("test@user.de")
            .hasPassword(password -> passwordEncoder.matches("secret", password));
    }
}
