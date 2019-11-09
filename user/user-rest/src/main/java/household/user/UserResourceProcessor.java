package household.user;

import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserResourceProcessor implements RepresentationModelProcessor<UserDTO> {

	private EntityLinks entityLinks;

	@Override
	public UserDTO process(UserDTO user) {
		if(user.getHouseholdId() != null) {
            user.add(new Link("api/households/" + user.getHouseholdId(), "household"));
		} else {
            user.add(new Link("api/households", "create"));
        }
        user.add(entityLinks.linkForItemResource(UserDTO.class, user.getDatabaseId()).slash("/invitations").withRel("invite"));
		user.getInvitations().forEach(invitation -> {
		    invitation.add(entityLinks.linkForItemResource(UserDTO.class, user.getDatabaseId()).slash("/invitations/").slash(invitation.getDatabaseId()).withRel("reject"));
		    invitation.add(entityLinks.linkForItemResource(UserDTO.class, user.getDatabaseId()).slash("/invitations/").slash(invitation.getDatabaseId()).withRel("accept"));
		});
		return user;
	}

}
