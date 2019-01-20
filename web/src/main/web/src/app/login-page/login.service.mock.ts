import { Observable, of } from 'rxjs';
import { User } from './user';
import { Injectable } from '@angular/core';

@Injectable()
export class LoginServiceMock {
    public login(email: string, password: string): Observable<User> {
        return of({
            email: 'user@email.de',
            invitations: [],
            links: []
        });
    }
}
