package household.foodplan;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(access=AccessLevel.PACKAGE, force = true)
@Getter
@EqualsAndHashCode
@ToString
class MealEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private final Long id;
	private String name;
	
	MealEntity(Long id, String name) {
		this.id = id;
		this.name = name;
	}
	
	void clear() {
		name = "";
	}
}
