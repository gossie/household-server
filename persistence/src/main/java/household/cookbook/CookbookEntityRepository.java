package household.cookbook;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.transaction.annotation.Transactional;

interface CookbookEntityRepository extends JpaRepository<CookbookEntity, Long> {

    @Transactional
    @Override
//    @Lock(LockModeType.READ)
    CookbookEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends CookbookEntity> S save(S entity);
}
