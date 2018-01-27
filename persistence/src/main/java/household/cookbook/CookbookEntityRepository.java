package household.cookbook;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface CookbookEntityRepository extends JpaRepository<CookbookEntity, Long> {

    @Override
    @Lock(LockModeType.PESSIMISTIC_READ)
    CookbookEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    <S extends CookbookEntity> S save(S entity);
}
