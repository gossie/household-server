package household.household;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HouseholdEntityRepository extends MongoRepository<HouseholdEntity, String> {

}
