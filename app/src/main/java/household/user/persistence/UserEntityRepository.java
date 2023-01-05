package household.user.persistence;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;

interface UserEntityRepository extends MongoRepository<UserEntity, String> {

	List<UserEntity> findByEmailIgnoreCase(String email);

    List<UserEntity> findByHouseholdId(String householdId);

}
