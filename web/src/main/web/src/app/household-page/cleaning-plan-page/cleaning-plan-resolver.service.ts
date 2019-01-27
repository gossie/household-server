import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from "@angular/router";
import { Observable } from "rxjs/index";
import { CleaningPlan } from "./cleaning-plan";
import { CleaningPlanService } from "./cleaning-plan.service";

@Injectable({
    providedIn: 'root'
})
export class CleaningPlanResolverService implements Resolve<CleaningPlan> {

    constructor(private cleaningPlanService: CleaningPlanService) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CleaningPlan> {
        return this.cleaningPlanService.determineCleaningPlan(route.paramMap.get('url'));
    }
}
