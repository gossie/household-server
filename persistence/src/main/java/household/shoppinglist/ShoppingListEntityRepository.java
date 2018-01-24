package household.shoppinglist;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface ShoppingListEntityRepository extends JpaRepository<ShoppingListEntity, Long> {

    @Override
    @Lock(LockModeType.READ)
    ShoppingListEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends ShoppingListEntity> S save(S entity);
}
