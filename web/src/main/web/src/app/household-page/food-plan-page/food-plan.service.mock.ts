import { Injectable } from '@angular/core';
import { FoodPlan } from "./food-plan";
import { Observable, of } from "rxjs/index";
import { Household } from "../household";

@Injectable()
export class FoodPlanServiceMock {

    constructor() { }

    public determineFoodPlan(household: Household): Observable<FoodPlan> {
        return of({
            meals: {
                monday: {
                    name: '',
                },
                tuesday: {
                    name: '',
                },
                wednesday: {
                    name: '',
                },
                thursday: {
                    name: '',
                },
                friday: {
                    name: '',
                },
                saturday: {
                    name: '',
                },
                sunday: {
                    name: '',
                }
            }
        });
    }

    public saveFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        return of();
    }

    public clearFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        return of();
    }
}
