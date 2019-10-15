import { Observable, of } from 'rxjs/index';
import { Injectable } from '@angular/core';
import { User } from "./user";

@Injectable({
    providedIn: 'root'
})
export class UserServiceMock {

    private user: User = {
        email: 'user@email.de',
        invitations: [],
        links: []
    };

    constructor() { }

    public setUser(user: User): void {
        this.user = user;
    }

    public getUser(): User {
        return this.user;
    }

    public observeUser(): Observable<User> {
        return of(this.user);
    }
}
