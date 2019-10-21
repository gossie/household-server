import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { UserService } from '../../user.service';
import { FoodPlan } from './food-plan';
import { Observable } from 'rxjs/index';
import { AbstractNetworkService } from '../../abstract-network.service';
import { Household } from '../household';
import { Meal } from './meal/meal';
import { Recipe } from '../cookbook-page/recipe/recipe';
import { ObjectUtils } from 'src/app/object.utils';

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

    public saveMeal(meal: Meal, recipe: Recipe): Observable<FoodPlan> {
        const url: string = this.determineUrl(meal, 'self');

        const body: object = {
            meal: meal,
            cookbookId: this.determineCookbookId(recipe),
            recipeId: this.determineRecipeId(recipe)
        };

        return this.httpClient.put<FoodPlan>(url, body, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    private determineCookbookId(recipe: Recipe): number {
        if (ObjectUtils.isObject(recipe)) {
            const urlComponents: Array<string> = this.determineUrl(recipe, 'self').split('/');
            return parseInt(urlComponents[urlComponents.length - 3], 10);
        }
        return null;
    }

    private determineRecipeId(recipe: Recipe): number {
        if (ObjectUtils.isObject(recipe)) {
            const urlComponents: Array<string> = this.determineUrl(recipe, 'self').split('/');
            return parseInt(urlComponents[urlComponents.length - 1], 10);
        }
        return null;
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
