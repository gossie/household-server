import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";
import { Household } from "./household";

@Injectable()
export class HouseholdServiceMock {

    constructor() {}

    public createHousehold(): Observable<Household> {
        return of();
    }
}
