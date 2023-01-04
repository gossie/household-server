package household.user.rest;

import org.springframework.stereotype.Component;

import household.user.domain.Invitation;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
class InvitationDTOMapper {

    InvitationDTO map(Invitation invitation) {
        return new InvitationDTO(invitation.getId(), invitation.getHouseholdId(), invitation.getSender());
    }
}
