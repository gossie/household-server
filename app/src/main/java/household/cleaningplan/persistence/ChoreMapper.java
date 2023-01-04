package household.cleaningplan.persistence;

import household.cleaningplan.domain.Chore;
import household.cleaningplan.domain.Repeat;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class ChoreMapper {

	Chore map(ChoreEntity from) {
		Repeat repeat = from.getRepeat().map(r -> new Repeat(r.getId(), r.getNumber(), household.cleaningplan.domain.TimeUnit.valueOf(r.getTimeUnit().name()))).orElse(null);
		return new Chore(from.getId(), from.getName(), from.getLastPerformed(), repeat);
	}

	ChoreEntity map(Chore from) {
		RepeatEntity repeat = from.getRepeat().map(r -> new RepeatEntity(r.getId(), r.getNumber(), TimeUnit.valueOf(r.getTimeUnit().name()))).orElse(null);
		return new ChoreEntity(from.getId(), from.getName(), from.getLastPerformed(), repeat);
	}
}
