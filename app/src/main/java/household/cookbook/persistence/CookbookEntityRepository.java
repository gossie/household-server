package household.cookbook.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

interface CookbookEntityRepository extends MongoRepository<CookbookEntity, String> {

}
