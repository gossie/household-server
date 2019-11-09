package household.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class UserContext {

	@Autowired
	private UserEntityRepository userEntityRepository;

	@Bean
	public InvitationEntityMapper invitationMapper() {
		return new InvitationEntityMapper();
	}

	@Bean
	public UserMapper userMapper() {
		return new UserMapper(invitationMapper());
	}

	@Bean
	public UserRepository userRepository() {
		return new DefaultUserRepository(userEntityRepository, userMapper(), passwordEncoder());
	}

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public ReactiveUserDetailsService userDetailsService() {
		return new CustomUserDetailsService(userRepository());
	}

    @Bean
    public ReactiveAuthenticationManager authenticationProvider() {
        UserDetailsRepositoryReactiveAuthenticationManager authenticationManager = new UserDetailsRepositoryReactiveAuthenticationManager(userDetailsService());
        authenticationManager.setPasswordEncoder(passwordEncoder());
        return authenticationManager;
    }
}