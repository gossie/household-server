import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import LoginData from './login-data';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private httpClient: HttpClient) { }

    public loginUser(loginData: LoginData): Observable<void> {
        return this.httpClient.post<void>(`${environment.apiUrl}/api/auth/login`, loginData, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                'Accepts': 'application/vnd.household.v1+json'
            }
        })
    }

}
