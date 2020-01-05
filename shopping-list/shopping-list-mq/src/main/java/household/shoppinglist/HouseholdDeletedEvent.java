package household.shoppinglist;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
class HouseholdDeletedEvent {

    private Long shoppingListId;

}
