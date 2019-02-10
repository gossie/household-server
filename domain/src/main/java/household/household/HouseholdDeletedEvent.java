package household.household;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class HouseholdDeletedEvent {

    private final Household household;

}
