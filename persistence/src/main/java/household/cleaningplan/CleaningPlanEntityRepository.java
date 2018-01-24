package household.cleaningplan;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

interface CleaningPlanEntityRepository extends JpaRepository<CleaningPlanEntity, Long> {

    @Transactional
    @Override
//    @Lock(LockModeType.READ)
    CleaningPlanEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends CleaningPlanEntity> S save(S entity);
}
