import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { UserService } from "../../user.service";
import { FoodPlan } from "./food-plan";
import { Observable } from "rxjs/index";
import {AbstractNetworkService} from "../../abstract-network.service";

@Injectable({
    providedIn: 'root'
})
export class FoodPlanService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineFoodPlan(url: string): Observable<FoodPlan> {
        return this.httpClient.get<FoodPlan>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public saveFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        const url: string = this.determineUrl(foodPlan, 'save');

        return this.httpClient.put<FoodPlan>(url, foodPlan, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
