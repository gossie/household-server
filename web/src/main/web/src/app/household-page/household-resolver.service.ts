import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Household } from './household';
import { Observable } from 'rxjs';
import { HouseholdService } from "./household.service";

@Injectable({
    providedIn: 'root'
})
export class HouseholdResolverService implements Resolve<Household> {

    constructor(private householdService: HouseholdService) { }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Household> {
        return this.householdService.determineHousehold();
    }

}
