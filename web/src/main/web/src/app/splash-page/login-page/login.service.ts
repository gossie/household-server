import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { User } from './user';
import { environment } from "../../../environments/environment";

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private httpClient: HttpClient) { }

    public login(email: string, password: string): Observable<User> {
        return this.httpClient.post<User>(`${environment.apiUrl}/users/login`, null, {
            headers: {
                Authorization: `Basic ${btoa(email.toLowerCase() + ':' + password)}`,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
