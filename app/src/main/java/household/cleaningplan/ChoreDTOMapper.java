package household.cleaningplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ChoreDTOMapper {

	public ChoreDTO map(Chore from) {
		long nextTime = from.getRepeat()
				.map(r -> from.getLastPerformed() + r.getTimeUnit().determineMilliseconds(r.getNumber()))
				.orElse(-1L);
		
		return new ChoreDTO(from.getId(), from.getName(), from.getLastPerformed(), nextTime);
	}

	public Chore map(ChoreDTO from) {
		return new Chore(from.getDatabaseId(), from.getName(), from.getLastPerformed());
	}
}
