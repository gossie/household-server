import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable } from 'rxjs';

@Injectable({
    providedIn: 'root',
})
export class TokenService {

    private subject = new BehaviorSubject(localStorage.getItem('jwt') ?? '');

    public publishToken(token: string) {
        localStorage.setItem('jwt', token);
        this.subject.next(token);
    }

    public observeToken(): Observable<string> {
        return this.subject.asObservable();
    }

}
