import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/index";
import { User } from "../login-page/user";

@Injectable({
    providedIn: 'root'
})
export class RegistrationService {

    constructor(private httpClient: HttpClient) { }

    public register(email: string, password: string): Observable<User> {
        const body: object = {
            email,
            password
        };
        return this.httpClient.post<User>('https://ldwas-household.herokuapp.com/api/users', body, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
