package household.user;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

import household.household.HouseholdDTO;
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
		    resource.add(entityLinks.linkForSingleResource(HouseholdDTO.class, user.getHouseholdId()).withRel("household"));
		} else {
            resource.add(entityLinks.linkFor(HouseholdDTO.class).withRel("create"));
        }
		resource.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).slash("/invitations").withRel("invite"));
		user.getInvitations().forEach(invitation -> {
		    invitation.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).slash("/invitations/").slash(invitation.getDatabaseId()).withRel("reject"));
		    invitation.add(entityLinks.linkForSingleResource(UserDTO.class, user.getDatabaseId()).slash("/invitations/").slash(invitation.getDatabaseId()).withRel("accept"));
		});
		return resource;
	}

}
