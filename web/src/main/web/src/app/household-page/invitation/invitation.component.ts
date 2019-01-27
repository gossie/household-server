import { Component, Input, OnInit } from '@angular/core';
import { Invitation } from "../../splash-page/login-page/invitation";
import { InvitationService } from "../cover-page/invitation.service";
import { User } from "../../splash-page/login-page/user";
import { UserService } from "../../user.service";
import { Router } from "@angular/router";
import { Page } from "../../page.enum";

@Component({
    selector: 'app-invitation',
    templateUrl: './invitation.component.html',
    styleUrls: ['./invitation.component.css']
})
export class InvitationComponent implements OnInit {

    @Input()
    public invitation: Invitation;

    constructor(private invitationService: InvitationService,
                private userService: UserService,
                private router: Router) { }

    ngOnInit() {
    }

    public acceptInvitation(): void {
        this.invitationService.acceptInvitation(this.invitation)
            .subscribe((user: User) => {
                this.userService.setUser(user);
                this.router.navigate([Page.Cover]);
            });
    }

    public rejectInvitation(): void {
        this.invitationService.rejectInvitation(this.invitation)
            .subscribe((user: User) => {
                this.userService.setUser(user);
            });
    }

}
