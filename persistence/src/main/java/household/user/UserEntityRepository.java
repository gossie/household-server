package household.user;

import java.util.List;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

interface UserEntityRepository extends JpaRepository<UserEntity, Long> {
    
    @Override
    @Transactional
//    @Lock(LockModeType.READ)
    UserEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends UserEntity> S save(S entity);

    @Transactional
//    @Lock(LockModeType.READ)
	List<UserEntity> findByEmail(String email);

    @Transactional
//    @Lock(LockModeType.READ)
    List<UserEntity> findByHouseholdId(Long householdId);

}
