package household.user.events;

import java.util.List;
import java.util.Optional;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
@EqualsAndHashCode
public class InvitationAcceptedEvent {

    private final String oldHouseholdId;
    private final List<String> leftUserIds;

    public Optional<String> getOldHouseholdId() {
        return Optional.ofNullable(oldHouseholdId);
    }
}
