package household.cleaningplan;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface CleaningPlanEntityRepository extends JpaRepository<CleaningPlanEntity, Long> {

    @Override
    @Lock(LockModeType.READ)
    CleaningPlanEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends CleaningPlanEntity> S save(S entity);
}
