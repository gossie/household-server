import { Injectable } from '@angular/core';
import { Observable } from "rxjs/index";
import { HttpClient } from "@angular/common/http";
import { UserService } from "../../user.service";
import { CleaningPlan } from './cleaning-plan';
import {AbstractNetworkService} from "../../abstract-network.service";
import {Chore} from "./chore/chore";

@Injectable({
    providedIn: 'root'
})
export class CleaningPlanService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineCleaningPlan(url: string): Observable<CleaningPlan> {
        return this.httpClient.get<CleaningPlan>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public addChore(cleaningPlan: CleaningPlan, chore: Chore): Observable<CleaningPlan> {
        const url: string = this.determineUrl(cleaningPlan, 'add');

        return this.httpClient.post<CleaningPlan>(url, chore, {
            headers: {
                Authorization: this.userService.getUserData().authData,
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
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public deleteChore(chore: Chore): Observable<CleaningPlan> {
        const url: string = this.determineUrl(chore, 'delete');
        return this.httpClient.delete<CleaningPlan>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
