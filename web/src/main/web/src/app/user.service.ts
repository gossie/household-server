import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';
import { filter, tap } from 'rxjs/internal/operators';
import { ObjectUtils } from './object.utils';
import { User } from "./user";
import { HttpClient } from "@angular/common/http";
import { AbstractNetworkService } from "./abstract-network.service";

@Injectable({
    providedIn: 'root'
})
export class UserService extends AbstractNetworkService {

    private userStream: BehaviorSubject<User> = new BehaviorSubject(null);

    constructor(private httpClient: HttpClient) {
        super();
    }

    public setUser(user: User) {
        this.userStream.next(user);
    }

    public getUser(): User {
        return this.userStream.getValue();
    }

    public observeUser(): Observable<User> {
        return this.userStream.asObservable()
          .pipe(
              filter((user: User) => ObjectUtils.isObject(user))
          );
    }

    public updateUser(): void {
        const user: User = this.getUser();
        const url: string = this.determineUrl(user, 'self');
        this.httpClient.get<User>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        })
        .subscribe((user: User) => this.setUser(user));
    }

    public changePassword(user: User, currentPassword: string, newPassword: string): Observable<User> {
        const currentUser: User = this.getUser();
        let url: string = this.determineUrl(currentUser, 'changePassword');
        if (url === undefined) {
            url = this.determineUrl(currentUser, 'self');
        }
        const body: object = {
            currentPassword,
            newPassword
        };
        const options = {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        };
        return this.httpClient.put<User>(url, body, options);
    }
}
