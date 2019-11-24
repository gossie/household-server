package household.cleaningplan;

public class Repeat extends AbstractModel {

    private final int number;
    private TimeUnit timeUnit = TimeUnit.DAYS;

    Repeat(Long id, int number) {
        super(id);
        this.number = number;
    }

    Repeat(Long id, int number, TimeUnit timeUnit) {
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
