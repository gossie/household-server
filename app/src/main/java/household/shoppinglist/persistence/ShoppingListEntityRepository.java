package household.shoppinglist.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

interface ShoppingListEntityRepository extends MongoRepository<ShoppingListEntity, String> {

}
