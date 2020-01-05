package household.cookbook;

import com.google.common.eventbus.EventBus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookbookMQContext {

    @Bean(initMethod = "init")
    public CookbookEventHandler cookbookEventHandler(EventBus eventBus, CookbookService cookbookService) {
        return new CookbookEventHandler(eventBus, cookbookService);
    }

}
