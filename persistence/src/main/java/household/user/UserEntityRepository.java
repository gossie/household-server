package household.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

	List<UserEntity> findByEmail(String email);
	
}