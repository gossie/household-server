package household.user;

import java.util.Map;

import org.springframework.hateoas.Link;
import org.springframework.http.HttpStatus;
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
import reactor.core.publisher.Mono;

import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.linkTo;
import static org.springframework.hateoas.server.reactive.WebFluxLinkBuilder.methodOn;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserDTOMapper userMapper;

    @PutMapping(path="/{userId}",  consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    public Mono<UserDTO> changePassword(@PathVariable Long userId, @RequestBody Map<String, String> data) {
        User user = userService.changePassword(userId, data.get("currentPassword"), data.get("newPassword"));
        return Mono.just(createResource(user))
            .flatMap(this::addLinks);
    }

    @GetMapping(path="/current", produces={"application/vnd.household.v1+json"})
    public Mono<UserDTO> getCurrentUser() {
        // TODO
        try {
            return userService.determineCurrentUser()
                .map(this::createResource)
                .flatMap(this::addLinks);
        } catch(IllegalStateException e) {
            throw new NotAuthenticatedException(e);
        }
    }

    @PostMapping(path="/{userId}/invitations", consumes={"application/vnd.household.v1+json"}, produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<UserDTO> invite(@PathVariable Long userId, @RequestBody InvitationRequestDTO invitation) {
        User invitingUser = userService.determineUser(userId);
        try {
            userService.invite(invitation.getEmail(), invitingUser);
            return Mono.just(createResource(invitingUser))
                .flatMap(this::addLinks);
        } catch(IllegalStateException e) {
            throw new NotFoundException(e);
        }
    }

    @PostMapping(path="/{userId}/invitations/{invitationId}", produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<UserDTO> acceptInvitation(@PathVariable Long userId, @PathVariable Long invitationId) {
        userService.acceptInvitation(userId, invitationId);
        return Mono.just(createResource(userService.determineUser(userId)))
            .flatMap(this::addLinks);
    }

    @DeleteMapping(path="/{userId}/invitations/{invitationId}", produces={"application/vnd.household.v1+json"})
    @ResponseStatus(value = HttpStatus.OK)
    public Mono<UserDTO> rejectInvitation(@PathVariable Long userId, @PathVariable Long invitationId) {
        userService.rejectInvitation(userId, invitationId);
        return Mono.just(createResource(userService.determineUser(userId)))
            .flatMap(this::addLinks);
    }

    private UserDTO createResource(User user) {
        return userMapper.map(user);
    }

    private Mono<UserDTO> addLinks(UserDTO user) {
        return addChangePasswordLink(user)
            .map(this::addHouseholdLinkLink);
    }

    private Mono<UserDTO> addChangePasswordLink(UserDTO user) {
        return linkTo(methodOn(UserController.class).getCurrentUser())
            .withRel("changePassword")
            .toMono()
            .map(user::add)
            .map(UserDTO.class::cast);
    }



    private UserDTO addHouseholdLinkLink(UserDTO user) {
        if (user.getHouseholdId() != null) {
            return (UserDTO) user.add(new Link("api/households/" + user.getHouseholdId(), "household"));
        } else {
            return (UserDTO) user.add(new Link("api/households", "create"));
        }
    }
/*
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

 */
}
