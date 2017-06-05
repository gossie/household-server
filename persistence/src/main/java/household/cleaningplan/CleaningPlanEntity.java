package household.cleaningplan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

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
class CleaningPlanEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@JsonIgnore
	private final Long id;
	@OneToMany(cascade=CascadeType.ALL)
	private List<ChoreEntity> chores = new ArrayList<>();

	void addChore(ChoreEntity chore) {
		chore.setLastPerformed(0);
		chores.add(chore);
	}

	void removeChore(long choreId) {
		chores.removeIf(c -> c.getId().longValue() == choreId);
	}
	
	void update(ChoreEntity input) {
		chores.stream()
				.filter(c -> c.getName().equals(input.getName()))
				.findFirst()
				.ifPresent(c -> c.setLastPerformed(input.getLastPerformed()));
	}

}
