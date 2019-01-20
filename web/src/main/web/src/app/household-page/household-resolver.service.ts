import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from '@angular/router';
import {Household} from './household';
import {Observable} from 'rxjs';
import {UserData} from '../user-data';
import {HttpClient} from '@angular/common/http';
import {AbstractNetworkService} from '../abstract-network.service';
import {UserService} from '../user.service';

@Injectable({
    providedIn: 'root'
})
export class HouseholdResolverService extends AbstractNetworkService implements Resolve<Household> {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Household> {
        return this.determineHousehold(this.userService.getUserData());
    }

    public determineHousehold(userData: UserData): Observable<Household> {
        const url: string = this.determineUrl(userData.user, 'household');
        return this.httpClient.get<Household>(url, {
            headers: {
                Authorization: userData.authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }
}
