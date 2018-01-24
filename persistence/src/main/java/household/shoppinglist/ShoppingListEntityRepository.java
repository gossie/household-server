package household.shoppinglist;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

interface ShoppingListEntityRepository extends JpaRepository<ShoppingListEntity, Long> {

    @Transactional
    @Override
//    @Lock(LockModeType.READ)
    ShoppingListEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends ShoppingListEntity> S save(S entity);
}
