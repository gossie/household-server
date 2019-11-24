import { Injectable } from '@angular/core';
import { FoodPlan } from './food-plan';
import { Observable, of } from 'rxjs/index';
import { Household } from '../household';
import { Meal } from './meal/meal';
import { Recipe } from '../cookbook-page/recipe/recipe';

@Injectable()
export class FoodPlanServiceMock {

    constructor() { }

    public determineFoodPlan(household: Household): Observable<FoodPlan> {
        return of({
            meals: {
                monday: {
                    name: '',
                    links: []
                },
                tuesday: {
                    name: '',
                    links: []
                },
                wednesday: {
                    name: '',
                    links: []
                },
                thursday: {
                    name: '',
                    links: []
                },
                friday: {
                    name: '',
                    links: []
                },
                saturday: {
                    name: '',
                    links: []
                },
                sunday: {
                    name: '',
                    links: []
                }
            }
        });
    }

     public saveMeal(meal: Meal, recipe: Recipe): Observable<FoodPlan> {
        return of();
    }

    public clearFoodPlan(foodPlan: FoodPlan): Observable<FoodPlan> {
        return of();
    }
}
