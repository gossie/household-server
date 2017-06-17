package household.user;

import java.util.Collections;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class CustomUserDetailsService implements UserDetailsService {

	private final UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepository.determineUser(email);
		return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority("ROLE_USER")));
	}

}