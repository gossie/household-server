package household.cleaningplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChoreMapper {

	public ChoreDTO map(ChoreEntity from) {
		long nextTime = from.getRepeat()
				.map(r -> from.getLastPerformed() + r.getTimeUnit().determineMilliseconds(r.getNumber()))
				.orElse(-1L);
		
		return new ChoreDTO(from.getId(), from.getName(), from.getLastPerformed(), nextTime);
	}

	public ChoreEntity map(ChoreDTO from) {
		return new ChoreEntity(from.getName(), from.getLastPerformed());
	}
}
