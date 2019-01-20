import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/index";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {FoodPlan} from "./food-plan";

@Injectable({
  providedIn: 'root'
})
export class FoodPlanResolverService implements Resolve<FoodPlan> {

    constructor(private userService: UserService,
                private httpClient: HttpClient) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<FoodPlan> {
        return this.httpClient.get<FoodPlan>(route.paramMap.get('url'), {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
