package household.user;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor(access=AccessLevel.PACKAGE)
class UserDTOMapper {

	private final InvitationDTOMapper invitationMapper;

    UserDTO map(User user) {
        List<InvitationDTO> invitations = user.getInvitations().stream().map(invitationMapper::map).collect(Collectors.toList());
        return new UserDTO(user.getId(), user.getEmail(), user.getHouseholdId(), invitations);
    }

}
