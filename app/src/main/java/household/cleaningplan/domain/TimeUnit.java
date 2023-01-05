package household.cleaningplan.domain;

public enum TimeUnit {

	WEEKS(1000 * 60 * 60 * 24 * 7),
	DAYS(1000 * 60 * 60 * 24),
	HOURS(1000 * 60 * 60);

	private long base;

	private TimeUnit(long base) {
		this.base = base;
	}

	public long determineMilliseconds(int number) {
		return base * number;
	}
}
