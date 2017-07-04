package household.cleaningplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import household.AbstractModel;

public class CleaningPlan extends AbstractModel {

	private List<Chore> chores;
	
	public CleaningPlan(Long id, List<Chore> chores) {
		super(id);
		this.chores = new ArrayList<>(chores);
	}
	
	public List<Chore> getChores() {
		return Collections.unmodifiableList(chores);
	}

	public void addChore(Chore chore) {
		chores.add(chore);
	}

	public void removeChore(long choreId) {
		chores.removeIf(c -> c.getId().longValue() == choreId);
	}
	
	public void update(Chore input) {
		chores.stream()
				.filter(c -> c.getName().equals(input.getName()))
				.findFirst()
				.ifPresent(c -> c.setLastPerformed(input.getLastPerformed()));
	}
}
