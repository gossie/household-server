import { Injectable } from '@angular/core';
import { Observable, of } from 'rxjs/index';
import { CleaningPlan } from './cleaning-plan';
import { Chore } from './chore/chore';
import { Household } from '../household';
import { Task } from './task/task';

@Injectable()
export class CleaningPlanServiceMock {

    constructor() { }

    public determineCleaningPlan(household: Household): Observable<CleaningPlan> {
        return of({
            chores: [],
            tasks: [],
            links: []
        });
    }

    public addChore(cleaningPlan: CleaningPlan, chore: Chore): Observable<CleaningPlan> {
        return of();
    }

    public addTask(cleaningPlan: CleaningPlan, task: Task): Observable<CleaningPlan> {
        return of();
    }
}
