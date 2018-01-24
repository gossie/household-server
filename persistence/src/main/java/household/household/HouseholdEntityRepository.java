package household.household;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

public interface HouseholdEntityRepository extends JpaRepository<HouseholdEntity, Long> {

    @Override
    @Transactional
//    @Lock(LockModeType.READ)
    HouseholdEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends HouseholdEntity> S save(S entity);
}
