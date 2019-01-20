import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/index";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {CleaningPlan} from "./cleaning-plan";

@Injectable({
    providedIn: 'root'
})
export class CleaningPlanResolverService implements Resolve<CleaningPlan> {

    constructor(private userService: UserService,
                private httpClient: HttpClient) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CleaningPlan> {
        return this.httpClient.get<CleaningPlan>(route.paramMap.get('url'), {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
