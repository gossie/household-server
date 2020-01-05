package household.cleaningplan;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
class HouseholdDeletedEvent {

    private Long cleaningPlanId;

}
