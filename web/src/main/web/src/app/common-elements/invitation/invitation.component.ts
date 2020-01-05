import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { Invitation } from "../../invitation";
import { InvitationService } from "./invitation.service";
import { User } from "../../user";
import { UserService } from "../../user.service";

@Component({
    selector: 'app-invitation',
    templateUrl: './invitation.component.html',
    styleUrls: ['./invitation.component.sass']
})
export class InvitationComponent implements OnInit, OnDestroy {

    @Input()
    public invitation: Invitation;


    constructor(private invitationService: InvitationService,
                private userService: UserService) { }

    public ngOnInit(): void {
    }

    public ngOnDestroy(): void {
    }

    public acceptInvitation(): void {
        this.invitationService.acceptInvitation(this.invitation)
            .subscribe((user: User) => {
                this.userService.setUser(user);
            });
    }

    public rejectInvitation(): void {
        this.invitationService.rejectInvitation(this.invitation)
            .subscribe((user: User) => {
                this.userService.setUser(user);
            });
    }

}
