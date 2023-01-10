import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import RegistrationData from './registration-data';

@Injectable({
    providedIn: 'root',
})
export class RegistrationService {

    constructor(private httpClient: HttpClient) { }

    public registerUser(registrationData: RegistrationData): Observable<void> {
        return this.httpClient.post<void>(`${environment.apiUrl}/registrations`, registrationData, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                'Accepts': 'application/vnd.household.v1+json'
            }
        })
    }

}
