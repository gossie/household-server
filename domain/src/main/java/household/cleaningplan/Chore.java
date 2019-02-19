package household.cleaningplan;

import java.util.Optional;

import household.AbstractModel;

public class Chore extends AbstractModel {

	private String name;
	private long lastPerformed;
	private Repeat repeat;

	Chore(Long id, String name, long lastPerformed) {
		this(id, name, lastPerformed, null);
	}

	Chore(Long id, String name, long lastPerformed, Repeat repeat) {
		super(id);
		this.name = name;
		this.lastPerformed = lastPerformed;
		this.repeat = repeat;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
	    this.name = name;
    }

	public long getLastPerformed() {
		return lastPerformed;
	}

	public void setLastPerformed(long lastPerformed) {
		this.lastPerformed = lastPerformed;
	}

	public long determineNextTime() {
		return getRepeat()
		    .map(r -> getLastPerformed() + r.getTimeUnit().determineMilliseconds(r.getNumber()))
		    .orElse(-1L);
	}

	public void setRepeat(Repeat repeat) {
	    this.repeat = repeat;
    }

	public Optional<Repeat> getRepeat() {
		return Optional.ofNullable(repeat);
	}
}
