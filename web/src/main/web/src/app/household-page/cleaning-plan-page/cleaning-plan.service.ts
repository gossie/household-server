import { Injectable } from '@angular/core';
import { Observable } from 'rxjs/index';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../user.service';
import { CleaningPlan } from './cleaning-plan';
import { AbstractNetworkService } from '../../abstract-network.service';
import { Chore } from './chore/chore';
import { Household } from '../household';
import { Task } from './task/Task';

@Injectable({
    providedIn: 'root'
})
export class CleaningPlanService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineCleaningPlan(household: Household): Observable<CleaningPlan> {
        const url: string = this.determineUrl(household, 'cleaningPlan');
        return this.httpClient.get<CleaningPlan>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public addChore(cleaningPlan: CleaningPlan, chore: Chore): Observable<CleaningPlan> {
        const url: string = this.determineUrl(cleaningPlan, 'addChore');

        return this.httpClient.post<CleaningPlan>(url, chore, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public selectChore(chore: Chore): Observable<CleaningPlan> {
        const url: string = this.determineUrl(chore, 'select');
        chore.lastPerformed = Date.now();

        return this.httpClient.patch<CleaningPlan>(url, chore, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public saveChore(chore: Chore): Observable<CleaningPlan> {
        const url: string = this.determineUrl(chore, 'save');

        return this.httpClient.patch<CleaningPlan>(url, chore, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public deleteChore(chore: Chore): Observable<CleaningPlan> {
        const url: string = this.determineUrl(chore, 'delete');
        return this.httpClient.delete<CleaningPlan>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public addTask(cleaningPlan: CleaningPlan, task: Task): Observable<CleaningPlan> {
        const url: string = this.determineUrl(cleaningPlan, 'addTask');

        return this.httpClient.post<CleaningPlan>(url, task, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public selectTask(task: Task): Observable<CleaningPlan> {
        const url: string = this.determineUrl(task, 'select');

        return this.httpClient.patch<CleaningPlan>(url, task, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
