package household.user;

import java.util.List;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.google.common.base.Preconditions;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class DefaultUserRepository implements UserRepository {

	private final UserEntityRepository userEntityRepository;
	private final UserMapper userMapper;
	private final PasswordEncoder passwordEncoder;

	@Override
	public User createUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		return userMapper.map(userEntityRepository.save(userMapper.map(user)));
	}

	@Override
	public User determineUser(Long userId) {
		UserEntity user = userEntityRepository.findOne(userId);
		return userMapper.map(user);
	}

	@Override
	public User determineUser(String email) {
		List<UserEntity> users = userEntityRepository.findByEmail(email);
		Preconditions.checkState(users.size() == 1, "found %s users with email %s", users.size(), email);
		return userMapper.map(users.get(0));
	}

	@Override
	public User determineCurrentUser() {
		return determineUser(SecurityContextHolder.getContext().getAuthentication().getName());
	}

	@Override
	public User saveUser(User user) {
		return userMapper.map(userEntityRepository.save(userMapper.map(user)));
	}

}
