package household.foodplan;

import org.springframework.data.mongodb.repository.MongoRepository;

interface FoodPlanEntityRepository extends MongoRepository<FoodPlanEntity, String> {

}
