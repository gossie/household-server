package household.cleaningplan;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleaningPlanPersistenceContext {
	
	@Autowired
	private CleaningPlanEntityRepository cleaningPlanEntityRepository;

	@Bean
	public ChoreMapper choreMapper() {
		return new ChoreMapper();
	}
	
	@Bean
	public CleaningPlanMapper cleaningPlanMapper() {
		return new CleaningPlanMapper(choreMapper());
	}
	
	@Bean
	public CleaningPlanRepository cleaningPlanRepository() {
		return new DefaultCleaningPlanRepository(cleaningPlanEntityRepository, cleaningPlanMapper());
	}
}
