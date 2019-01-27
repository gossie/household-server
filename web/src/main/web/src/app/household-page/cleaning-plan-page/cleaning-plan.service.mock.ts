import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";
import { CleaningPlan } from './cleaning-plan';
import { Chore } from "./chore/chore";

@Injectable()
export class CleaningPlanServiceMock {

    constructor() { }

    public determineCleaningPlan(url: string): Observable<CleaningPlan> {
        return of();
    }

    public addChore(cleaningPlan: CleaningPlan, chore: Chore): Observable<CleaningPlan> {
        return of();
    }
}
