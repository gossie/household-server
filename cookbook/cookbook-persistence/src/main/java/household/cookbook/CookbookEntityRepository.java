package household.cookbook;

import org.springframework.data.mongodb.repository.MongoRepository;

interface CookbookEntityRepository extends MongoRepository<CookbookEntity, String> {

}
