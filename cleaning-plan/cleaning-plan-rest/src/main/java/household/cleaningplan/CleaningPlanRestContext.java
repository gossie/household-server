package household.cleaningplan;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CleaningPlanRestContext {

    @Bean
    public ChoreDTOMapper choreDTOMapper() {
        return new ChoreDTOMapper();
    }

    @Bean
    public CleaningPlanDTOMapper cleaningPlanDTOMapper() {
        return new CleaningPlanDTOMapper(choreDTOMapper());
    }

}
