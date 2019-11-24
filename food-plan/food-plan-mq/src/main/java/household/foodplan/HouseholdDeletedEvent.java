package household.foodplan;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
class HouseholdDeletedEvent {

    private  Long foodPlanId;

}
