package household.cookbook;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
@EnableBinding(HouseholdMessageChannels.class)
class CookbookEventHandler {

    private final CookbookService cookbookService;

    @StreamListener(HouseholdMessageChannels.PRODUCER)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        cookbookService.deleteCookbook(event.getCookbookId());
    }

}
