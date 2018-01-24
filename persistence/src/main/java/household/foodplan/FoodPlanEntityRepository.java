package household.foodplan;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

interface FoodPlanEntityRepository extends JpaRepository<FoodPlanEntity, Long> {

    @Transactional
    @Override
//    @Lock(LockModeType.READ)
    FoodPlanEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends FoodPlanEntity> S save(S entity);
}
