import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter } from 'rxjs/internal/operators';
import { UserData } from './user-data';
import { ObjectUtils } from './object.utils';
import {User} from "./splash-page/login-page/user";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    private userStream: BehaviorSubject<UserData> = new BehaviorSubject(null);

    constructor() {
        const userData: string = localStorage.getItem('userData');
        if (userData) {
            this.userStream.next(JSON.parse(userData));
        }
    }

    public setUserData(userData: UserData) {
        localStorage.setItem('userData', JSON.stringify(userData));
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
