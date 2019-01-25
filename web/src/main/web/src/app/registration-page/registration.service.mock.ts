import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";
import { User } from "../login-page/user";

@Injectable()
export class RegistrationServiceMock {

    constructor() { }

    public register(email: string, password: string): Observable<User> {
        return of();
    }
}
