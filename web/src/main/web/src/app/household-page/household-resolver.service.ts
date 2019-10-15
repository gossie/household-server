import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot } from '@angular/router';
import { Household } from './household';
import { Observable } from 'rxjs';
import { HouseholdService } from './household.service';
import { UserService } from '../user.service';
import { User } from '../user';

@Injectable({
    providedIn: 'root'
})
export class HouseholdResolverService implements Resolve<Household> {

    constructor(private householdService: HouseholdService,
                private userService: UserService) { }

    async resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Promise<Household> {
        const user: User = await this.userService.determineCurrentUser().toPromise();
        return this.householdService.determineHousehold().toPromise();
    }

}
