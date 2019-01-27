import { Injectable } from '@angular/core';
import { FoodPlan } from "./food-plan";
import { Observable, of } from "rxjs/index";

@Injectable()
export class FoodPlanServiceMock {

    constructor() { }

    public determineFoodPlan(url: string): Observable<FoodPlan> {
        return of();
    }

    public saveFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        return of();
    }

    public clearFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        return of();
    }
}
