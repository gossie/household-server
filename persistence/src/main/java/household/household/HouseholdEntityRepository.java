package household.household;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

public interface HouseholdEntityRepository extends JpaRepository<HouseholdEntity, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    HouseholdEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends HouseholdEntity> S save(S entity);
}
