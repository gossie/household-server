package household.cleaningplan;

public class Task extends AbstractModel {

    private String name;
    private boolean done;

    Task(Long id, String name, boolean done) {
        super(id);
        this.name = name;
        this.done = done;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
