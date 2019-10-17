import { Injectable } from '@angular/core';
import { HttpClient } from "@angular/common/http";
import { UserService } from "../../user.service";
import { FoodPlan } from "./food-plan";
import { Observable } from "rxjs/index";
import { AbstractNetworkService } from "../../abstract-network.service";
import { Household } from "../household";

@Injectable({
    providedIn: 'root'
})
export class FoodPlanService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineFoodPlan(household: Household): Observable<FoodPlan> {
        const url: string = this.determineUrl(household, 'foodPlan');
        return this.httpClient.get<FoodPlan>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public saveFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        const url: string = this.determineUrl(foodPlan, 'save');

        return this.httpClient.put<FoodPlan>(url, foodPlan, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public clearFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        const url: string = this.determineUrl(foodPlan, 'clear');

        return this.httpClient.delete<FoodPlan>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
