package household.user;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    
    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    UserEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends UserEntity> S save(S entity);

    @Lock(LockModeType.PESSIMISTIC_READ)
	List<UserEntity> findByEmail(String email);

    @Lock(LockModeType.PESSIMISTIC_READ)
    List<UserEntity> findByHouseholdId(Long householdId);

}
