package household.household.persistence;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Document(collection = "households")
@Data
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
public class HouseholdEntity {

	@Id
	private String id;
	private String shoppingListId;
	private String cleaningPlanId;
	private String foodPlanId;
	private String cookbookId;

}
