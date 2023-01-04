package household.household.persistence;

import household.household.domain.HouseholdRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class HouseholdPersistenceContext {

	@Autowired
	private HouseholdEntityRepository householdEntityRepository;

	@Bean
	public HouseholdMapper householdMapper() {
		return new HouseholdMapper();
	}

	@Bean
	public HouseholdRepository householdRepository() {
		return new DefaultHouseholdRepository(householdEntityRepository, householdMapper());
	}
}
