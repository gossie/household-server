import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs/index';
import { Household } from './household';
import { User } from '../user';

@Injectable()
export class HouseholdServiceMock {

    constructor() {}

    public createHousehold(): Observable<Household> {
        return of();
    }

    public determineHousehold(): Observable<Household> {
        return of();
    }

    public updateHousehold(user: User): void {}

    public observeHousehold(): Observable<Household> {
        return of({
            participants: [],
            links: []
        });
    }
}
