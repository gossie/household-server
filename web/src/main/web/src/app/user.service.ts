import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter } from 'rxjs/internal/operators';
import { UserData } from './user-data';
import { ObjectUtils } from './object.utils';
import {User} from "./login-page/user";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private userStream: BehaviorSubject<UserData> = new BehaviorSubject(null);

    constructor() { }

    public setUserData(userData: UserData) {
        this.userStream.next(userData);
    }

    public setUser(user: User) {
        const userData: UserData = this.getUserData();
        userData.user = user;
        this.setUserData(userData);
    }

    public getUserData(): UserData {
        return this.userStream.getValue();
    }

    public observeUserData(): Observable<UserData> {
        return this.userStream.asObservable()
          .pipe(
              filter((userData: UserData) => ObjectUtils.isObject(userData))
          );
    }
}
