import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { LoginRequest, LoginResponse } from './login-data';

@Injectable({
    providedIn: 'root'
})
export class LoginService {

    constructor(private httpClient: HttpClient) { }

    public loginUser(loginData: LoginRequest): Observable<LoginResponse> {
        return this.httpClient.post<LoginResponse>(`${environment.apiUrl}/auth/login`, loginData, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                'Accepts': 'application/vnd.household.v1+json'
            }
        })
    }

}
