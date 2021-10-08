package household.user;

import java.util.List;
import java.util.Optional;

public record InvitationAcceptedEvent(Long oldHouseholdId, List<Long> leftUserIds) {

    public Optional<Long> getOldHouseholdId() {
        return Optional.ofNullable(oldHouseholdId);
    }
    
}
