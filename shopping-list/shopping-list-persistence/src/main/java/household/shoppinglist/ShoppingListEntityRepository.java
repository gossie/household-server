package household.shoppinglist;

import org.springframework.data.mongodb.repository.MongoRepository;

interface ShoppingListEntityRepository extends MongoRepository<ShoppingListEntity, String> {

}
