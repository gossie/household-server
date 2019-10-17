package household.user;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
<<<<<<< HEAD
    
    List<UserEntity> findByEmail(String email);
=======

	List<UserEntity> findByEmail(String email);
>>>>>>> master

    List<UserEntity> findByHouseholdId(Long householdId);

}
