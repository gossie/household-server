package household.cleaningplan.domain;

public class Repeat extends AbstractModel {

    private final int number;
    private TimeUnit timeUnit = TimeUnit.DAYS;

    public Repeat(String id, int number) {
        super(id);
        this.number = number;
    }

    public Repeat(String id, int number, TimeUnit timeUnit) {
        super(id);
        this.number = number;
        this.timeUnit = timeUnit;
    }

    public int getNumber() {
        return number;
    }

    public TimeUnit getTimeUnit() {
        return timeUnit;
    }
}
