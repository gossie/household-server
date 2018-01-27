package household.shoppinglist;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface ShoppingListEntityRepository extends JpaRepository<ShoppingListEntity, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    ShoppingListEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends ShoppingListEntity> S save(S entity);
}
