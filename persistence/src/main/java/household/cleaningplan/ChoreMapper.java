package household.cleaningplan;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ChoreMapper {

	public Chore map(ChoreEntity from) {
		Repeat repeat = from.getRepeat().map(r -> new Repeat(r.getId(), r.getNumber(), r.getTimeUnit())).orElse(null);
		return new Chore(from.getId(), from.getName(), from.getLastPerformed(), repeat);
	}

	public ChoreEntity map(Chore from) {
		RepeatEntity repeat = from.getRepeat().map(r -> new RepeatEntity(r.getId(), r.getNumber(), r.getTimeUnit())).orElse(null);
		return new ChoreEntity(from.getId(), from.getName(), from.getLastPerformed(), repeat);
	}
}
