package household.cookbook;

import household.HouseholdMessageChannels;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.context.annotation.Bean;

@EnableBinding(HouseholdMessageChannels.class)
public class CookbookMQContext {

    @Bean
    public CookbookEventHandler cookbookEventHandler(CookbookService cookbookService) {
        return new CookbookEventHandler(cookbookService);
    }

}
