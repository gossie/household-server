package household.shoppinglist;

import javax.persistence.Entity;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@Setter
@ToString
public class ShoppingListItemEntity extends AbstractEntity {

	private String name;
	private boolean selected;
}
