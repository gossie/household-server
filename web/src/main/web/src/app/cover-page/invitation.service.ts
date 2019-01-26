import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/index";
import { UserService } from "../user.service";
import { AbstractNetworkService} from "../abstract-network.service";
import { User } from "../login-page/user";
import {Invitation} from "../login-page/invitation";

@Injectable({
    providedIn: 'root'
})
export class InvitationService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public sendInvitation(email: string): Observable<User> {
        const url: string = this.determineUrl(this.userService.getUserData().user, 'invite');
        const body: object = {
            email
        };

        return this.httpClient.post<User>(url, body, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public acceptInvitation(invitation: Invitation): Observable<User> {
        const url: string = this.determineUrl(invitation, 'accept');
        return this.httpClient.post<User>(url, null, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public rejectInvitation(invitation: Invitation): Observable<User> {
        const url: string = this.determineUrl(invitation, 'reject');
        return this.httpClient.delete<User>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
