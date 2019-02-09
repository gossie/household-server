import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";
import { Household } from "./household";
import {UserData} from "../user-data";

@Injectable()
export class HouseholdServiceMock {

    constructor() {}

    public createHousehold(): Observable<Household> {
        return of();
    }

    public determineHousehold(): Observable<Household> {
        return of();
    }

    public updateHousehold(userData: UserData): void {}

    public observeHousehold(): Observable<Household> {
        return of({
            participants: [],
            links: []
        });
    }
}
