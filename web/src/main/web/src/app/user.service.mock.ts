import { Observable, of } from "rxjs/index";
import { UserData } from "./user-data";
import { Injectable } from "@angular/core";

@Injectable({
    providedIn: 'root'
})
export class UserServiceMock {

    private readonly userData: UserData = {
        user: {
            email: 'user@email.de',
            invitations: [],
            links: []
        },
        authData: 'authData'
    }

    constructor() { }

    public setUserData(userData: UserData): void {
    }

    public getUserData(): UserData {
        return this.userData;
    }

    public observeUserData(): Observable<UserData> {
        return of(this.userData);
    }
}
