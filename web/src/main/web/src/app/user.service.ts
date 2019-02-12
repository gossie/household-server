import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter } from 'rxjs/internal/operators';
import { UserData } from './user-data';
import { ObjectUtils } from './object.utils';
import { User } from "./splash-page/login-page/user";
import { HttpClient } from "@angular/common/http";
import { AbstractNetworkService } from "./abstract-network.service";

@Injectable({
    providedIn: 'root'
})
export class UserService extends AbstractNetworkService {

    private userStream: BehaviorSubject<UserData> = new BehaviorSubject(null);

    constructor(private httpClient: HttpClient) {
        super();
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

    public updateUser(): void {
        const userData = this.getUserData();
        const url: string = this.determineUrl(userData.user, 'self');
        this.httpClient.get<User>(url, {
            headers: {
                Authorization: userData.authData,
                Accept: 'application/vnd.household.v1+json'
            }
        })
        .subscribe((user: User) => this.setUser(user));
    }
}
