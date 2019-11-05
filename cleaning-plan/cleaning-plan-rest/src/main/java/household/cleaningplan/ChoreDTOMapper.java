package household.cleaningplan;

import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class ChoreDTOMapper {

    ChoreDTO map(Chore from) {
        long nextTime = from.getRepeat()
                .map(r -> from.getLastPerformed() + r.getTimeUnit().determineMilliseconds(r.getNumber()))
                .orElse(-1L);

        int repeat = from.getRepeat()
                .map(Repeat::getNumber)
                .orElse(0);

        return new ChoreDTO(from.getId(), from.getName(), from.getLastPerformed(), nextTime, repeat);
    }

    Chore map(ChoreDTO from) {
        return new Chore(from.getDatabaseId(), from.getName(), from.getLastPerformed(), new Repeat(null, from.getRepeat()));
    }

    Chore map(Long choreId, ChoreDTO from) {
        return new Chore(choreId, from.getName(), from.getLastPerformed(), new Repeat(null, from.getRepeat()));
    }
}
