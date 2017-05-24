package household.cookbook;

import javax.persistence.Entity;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@ToString
public class Ingredient extends AbstractEntity {

	private double amount;
	private String unit;
	private String name;
}
