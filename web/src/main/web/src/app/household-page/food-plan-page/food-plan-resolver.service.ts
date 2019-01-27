import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs/index";
import { FoodPlan } from "./food-plan";
import { FoodPlanService } from "./food-plan.service";

@Injectable({
    providedIn: 'root'
})
export class FoodPlanResolverService implements Resolve<FoodPlan> {

    constructor(private foodPlanService: FoodPlanService) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FoodPlan> {
        return this.foodPlanService.determineFoodPlan(route.paramMap.get('url'));
    }
}
