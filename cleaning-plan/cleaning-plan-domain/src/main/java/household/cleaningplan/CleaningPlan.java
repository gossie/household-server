package household.cleaningplan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CleaningPlan extends AbstractModel {

	private final List<Chore> chores;
    private final List<Task> tasks;

	public CleaningPlan(Long id, List<Chore> chores, List<Task> tasks) {
		super(id);
		this.chores = new ArrayList<>(chores);
        this.tasks = new ArrayList<>(tasks);
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
            .filter(c -> c.getId().equals(input.getId()))
            .findFirst()
            .ifPresent(c -> {
                c.setName(input.getName());
                c.setLastPerformed(input.getLastPerformed());
                input.getRepeat().ifPresent(c::setRepeat);
            });
    }

    public void update(Task task) {
        tasks.stream()
            .filter(c -> c.getId().equals(task.getId()))
            .findFirst()
            .ifPresent(c -> {
                c.setName(task.getName());
                c.setDone(task.isDone());
            });
    }

    public List<Task> getTasks() {
        return Collections.unmodifiableList(tasks);
    }

    public void addTask(Task task) {
        tasks.add(task);
    }
}
