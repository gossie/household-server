package household.cookbook;

import javax.persistence.LockModeType;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

interface CookbookEntityRepository extends JpaRepository<CookbookEntity, Long> {

    @Override
    @Lock(LockModeType.READ)
    CookbookEntity findOne(Long id);
    
    @Override
    @Lock(LockModeType.WRITE)
    <S extends CookbookEntity> S save(S entity);
}
