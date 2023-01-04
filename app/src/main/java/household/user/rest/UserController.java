package household.user.rest;

import java.security.Principal;
import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDTOMapper userMapper;

    @PutMapping(path="/{userId}",  consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public UserDTO changePassword(@PathVariable String userId, @RequestBody Map<String, String> data) {
        // TODO make safe
        User user = userService.changePassword(userId, data.get("currentPassword"), data.get("newPassword"));
        return addLinks(createResource(user));
    }

    @GetMapping(path="/current", produces={"application/vnd.household.v1+json"})
    public ResponseEntity<UserDTO> getCurrentUser(Principal principal) {
        // TODO
        try {
            return ResponseEntity.of(userService.determineUserByEmail(principal.getName())
                .map(this::createResource)
                .map(this::addLinks));
        } catch(IllegalStateException e) {
            throw new NotAuthenticatedException(e);
        }
    }

    @PostMapping(path="/{userId}/invitations", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO invite(@PathVariable String userId, @RequestBody InvitationRequestDTO invitation) {
        User invitingUser = userService.determineUserById(userId);
        try {
            userService.invite(invitation.getEmail(), invitingUser);
            return addLinks(createResource(invitingUser));
        } catch(IllegalStateException e) {
            throw new NotFoundException(e);
        }
    }

    @PostMapping(path="/{userId}/invitations/{invitationId}", produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO acceptInvitation(@PathVariable String userId, @PathVariable String invitationId) {
        userService.acceptInvitation(userId, invitationId);
        return addLinks(createResource(userService.determineUserById(userId)));
    }

    @DeleteMapping(path="/{userId}/invitations/{invitationId}", produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public UserDTO rejectInvitation(@PathVariable String userId, @PathVariable String invitationId) {
        userService.rejectInvitation(userId, invitationId);
        return addLinks(createResource(userService.determineUserById(userId)));
    }

    private UserDTO createResource(User user) {
        return userMapper.map(user);
    }

    private UserDTO addLinks(UserDTO user) {
        return addChangePasswordLink(addHouseholdLinkLink(addInviteLink(user))).getInvitations().stream()
            .map(invitation -> addAcceptLink(addRejectLink(invitation, user), user))
            .reduce(user, (u, i) -> u, (i, u) -> u);
    }

    private UserDTO addChangePasswordLink(UserDTO user) {
        return (UserDTO) user.add(Link.of("/api/users/" + user.getDatabaseId(), "changePassword"));
    }

    private UserDTO addHouseholdLinkLink(UserDTO user) {
        if (user.getHouseholdId() != null) {
            return (UserDTO) user.add(Link.of("api/households/" + user.getHouseholdId(), "household"));
        } else {
            return (UserDTO) user.add(Link.of("api/households", "create"));
        }
    }

    private UserDTO addInviteLink(UserDTO user) {
        return (UserDTO) user.add(Link.of("/api/users/" + user.getDatabaseId() + "/invitations", "invite"));
    }

    private InvitationDTO addAcceptLink(InvitationDTO invitation, UserDTO user) {
        return (InvitationDTO) invitation.add(Link.of("/api/users/" + user.getDatabaseId() + "/invitations/" + invitation.getDatabaseId(), "accept"));
    }

    private InvitationDTO addRejectLink(InvitationDTO invitation, UserDTO user) {
        return (InvitationDTO) invitation.add(Link.of("/api/users/" + user.getDatabaseId() + "/invitations/" + invitation.getDatabaseId(), "reject"));
    }

}
