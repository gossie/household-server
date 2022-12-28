package household.user;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class UserContext {

	private final UserEntityRepository userEntityRepository;

	@Bean
	InvitationEntityMapper invitationMapper() {
		return new InvitationEntityMapper();
	}

	@Bean
	UserMapper userMapper() {
		return new UserMapper(invitationMapper());
	}

	@Bean
	UserRepository userRepository() {
		return new DefaultUserRepository(userEntityRepository, userMapper(), passwordEncoder());
	}

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    UserDetailsService userDetailsService() {
		return new CustomUserDetailsService(userRepository());
	}

}
