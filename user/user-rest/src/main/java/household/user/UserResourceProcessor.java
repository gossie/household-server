package household.user;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component
public class UserResourceProcessor implements ResourceProcessor<Resource<UserDTO>> {

	private final EntityLinks entityLinks;

	@Override
	public Resource<UserDTO> process(Resource<UserDTO> resource) {
		UserDTO user = resource.getContent();
        resource.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).withSelfRel());
        resource.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).withRel("changePassword"));
		if(user.getHouseholdId() != null) {
		    resource.add(new Link("api/households/" + user.getHouseholdId(), "household"));
		} else {
            resource.add(new Link("api/households", "create"));
        }
		resource.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).slash("/invitations").withRel("invite"));
		user.getInvitations().forEach(invitation -> {
		    invitation.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).slash("/invitations/").slash(invitation.getDatabaseId()).withRel("reject"));
		    invitation.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).slash("/invitations/").slash(invitation.getDatabaseId()).withRel("accept"));
		});
		return resource;
	}

}
