package household.cleaningplan;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface CleaningPlanEntityRepository extends JpaRepository<CleaningPlanEntity, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    CleaningPlanEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends CleaningPlanEntity> S save(S entity);
}
