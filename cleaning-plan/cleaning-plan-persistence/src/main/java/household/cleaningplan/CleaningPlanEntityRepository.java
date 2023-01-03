package household.cleaningplan;

import org.springframework.data.mongodb.repository.MongoRepository;

interface CleaningPlanEntityRepository extends MongoRepository<CleaningPlanEntity, String> {

}
