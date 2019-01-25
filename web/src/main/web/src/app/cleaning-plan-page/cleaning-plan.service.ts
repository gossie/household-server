import { Injectable } from '@angular/core';
import { Observable } from "rxjs/index";
import { HttpClient } from "@angular/common/http";
import { UserService } from "../user.service";
import { CleaningPlan } from './cleaning-plan';

@Injectable({
    providedIn: 'root'
})
export class CleaningPlanService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) { }

    public determineCleaningPlan(url: string): Observable<CleaningPlan> {
        return this.httpClient.get<CleaningPlan>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
