import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private httpClient: HttpClient) { }

    public login(email: string, password: string): Observable<User> {
        return this.httpClient.post<User>('https://ldwas-household.herokuapp.com/api/users/login', null, {
            headers: {
                Authorization: `Basic ${btoa(email.toLowerCase() + ':' + password)}`,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
