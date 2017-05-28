package household.cleaningplan;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import household.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor(force=true)
@AllArgsConstructor
@Getter
@ToString
public class CleaningPlanEntity extends AbstractEntity {

	@OneToMany(cascade=CascadeType.ALL)
	private List<ChoreEntity> chores = new ArrayList<>();

	public void addChore(ChoreEntity chore) {
		chore.setLastPerformed(0);
		chores.add(chore);
	}

	public void removeChore(long choreId) {
		chores.removeIf(c -> c.getId().longValue() == choreId);
	}
	
	public void update(ChoreEntity input) {
		chores.stream()
				.filter(c -> c.getName().equals(input.getName()))
				.findFirst()
				.ifPresent(c -> c.setLastPerformed(input.getLastPerformed()));
	}

}
