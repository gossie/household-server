package household.foodplan;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface FoodPlanEntityRepository extends JpaRepository<FoodPlanEntity, Long> {

    @Override
    @Lock(LockModeType.READ)
    FoodPlanEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends FoodPlanEntity> S save(S entity);
}
