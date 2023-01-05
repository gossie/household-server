package household.foodplan.persistence;

import org.springframework.data.mongodb.repository.MongoRepository;

interface FoodPlanEntityRepository extends MongoRepository<FoodPlanEntity, String> {

}
