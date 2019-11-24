import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Observable } from 'rxjs/index';
import { Household } from './household';
import { HouseholdService } from './household.service';
import { UserService } from '../user.service';
import { User } from '../user';
import { ObjectUtils } from '../object.utils';

@Injectable({
    providedIn: 'root'
})
export class HouseholdResolverService implements Resolve<Household> {

    constructor(private householdService: HouseholdService,
                private userService: UserService) { }

    async resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Household> {
        const user: User = await this.userService.determineCurrentUser().toPromise();
        const householdObservable: Observable<Household> = this.householdService.determineHousehold();
        if (ObjectUtils.isObject(householdObservable)) {
            return householdObservable.toPromise();
        }
        return undefined;
    }

}
