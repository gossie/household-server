package household.user;

import java.util.List;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class InvitationAcceptedEvent {

    private final Long oldHouseholdId;
    private final List<User> leftUsers;

    public Optional<Long> getOldHouseholdId() {
        return Optional.ofNullable(oldHouseholdId);
    }
}
