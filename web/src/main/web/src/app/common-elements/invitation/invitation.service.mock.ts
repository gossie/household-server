import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";
import { User } from "../../user";

@Injectable()
export class InvitationServiceMock {

    constructor() { }

    public sendInvitation(): Observable<User> {
        return of();
    }
}
