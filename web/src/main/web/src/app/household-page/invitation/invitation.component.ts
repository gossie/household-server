import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import { Invitation } from "../../splash-page/login-page/invitation";
import { InvitationService } from "../cover-page/invitation.service";
import { User } from "../../splash-page/login-page/user";
import { UserService } from "../../user.service";

@Component({
    selector: 'app-invitation',
    templateUrl: './invitation.component.html',
    styleUrls: ['./invitation.component.css']
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
