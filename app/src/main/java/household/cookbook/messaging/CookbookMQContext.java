package household.cookbook.messaging;

import com.google.common.eventbus.EventBus;
import household.cookbook.domain.CookbookService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookbookMQContext {

    @Bean(initMethod = "init")
    public CookbookEventHandler cookbookEventHandler(EventBus eventBus, CookbookService cookbookService) {
        return new CookbookEventHandler(eventBus, cookbookService);
    }

}
