package household.cookbook;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class CookbookMQContext {

    public CookbookEventHandler cookbookEventHandler(CookbookService cookbookService) {
        return new CookbookEventHandler(cookbookService);
    }

}
