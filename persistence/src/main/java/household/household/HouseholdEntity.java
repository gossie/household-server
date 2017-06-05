package household.household;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force=true)
@AllArgsConstructor(access=AccessLevel.PACKAGE)
@Getter
@ToString
public class HouseholdEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	private Long shoppingListId;
	private Long cleaningPlanId;
	private Long foodPlanId;
	private Long cookbookId;

}
