package household.user;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
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
        UserEntity user = userEntityRepository.findById(userId).orElseThrow(IllegalStateException::new);
        return userMapper.map(user);
    }

    @Override
    public Optional<User> determineUser(String email) {
        List<UserEntity> users = userEntityRepository.findByEmail(email);
        if (users.isEmpty()) {
            return Optional.empty();
        } else if (users.size() == 1) {
            return Optional.ofNullable(userMapper.map(users.get(0)));
        } else {
            throw new IllegalStateException("found " + users.size() + " users with email " + email);
        }
    }

    @Override
    public User determineCurrentUser() {
        return determineUser(SecurityContextHolder.getContext().getAuthentication().getName()).orElseThrow(IllegalStateException::new);
    }

    @Override
    public List<User> determineUsers(Long householdId) {
        return userEntityRepository.findByHouseholdId(householdId)
                .stream()
                .map(userMapper::map)
                .collect(Collectors.toList());
    }

    @Override
    public User saveUser(User user) {
        return userMapper.map(userEntityRepository.save(userMapper.map(user)));
    }

    @Override
    public User saveUserAndHashPassword(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.map(userEntityRepository.save(userMapper.map(user)));
    }

}
