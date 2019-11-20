package household.cookbook;

import household.HouseholdMessageChannels;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.stream.annotation.StreamListener;

@RequiredArgsConstructor
class CookbookEventHandler {

    private final CookbookService cookbookService;

    @StreamListener(HouseholdMessageChannels.DELETION_INPUT)
    public void onHouseholdDeleted(HouseholdDeletedEvent event) {
        cookbookService.deleteCookbook(event.getCookbookId());
    }

}
