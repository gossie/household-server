import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs/index';
import { Household } from './household';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { AbstractNetworkService } from '../abstract-network.service';
import { User } from '../user';
import { filter, tap } from 'rxjs/internal/operators';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class HouseholdService extends AbstractNetworkService {

    private subject: BehaviorSubject<Household | string> = new BehaviorSubject(undefined);

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public createHousehold(): Observable<Household> {
        const url: string = this.determineUrl(this.userService.getUser(), 'create');
        return this.httpClient.post<Household>(`${environment.apiUrl}${url}`, null, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        }).pipe(
            tap((household: Household) => this.subject.next(household))
        );
    }

    public determineHousehold(): Observable<Household> {
        const user: User = this.userService.getUser();
        const url: string = this.determineUrl(user, 'household');
        if (url !== undefined) {
            return this.httpClient.get<Household>(`${environment.apiUrl}${url}`, {
                headers: {
                    Accept: 'application/vnd.household.v1+json'
                }
            }).pipe(
                tap((household: Household) => this.subject.next(household))
            );
        } else {
            return undefined;
        }
    }

    public updateHousehold(user: User): void {
        const url: string = this.determineUrl(user, 'household');
        console.debug('determine household for user', user, url)
        if (url !== undefined) {
            this.httpClient.get<Household>(`${environment.apiUrl}${url}`, {
                headers: {
                    Accept: 'application/vnd.household.v1+json'
                }
            })
            .subscribe((household: Household) => {
                console.debug('got household for user', user, household)
                this.subject.next(household)
            });
        } else {
            console.debug('user does not have a household yet, sending no-household', user);
            this.subject.next('no-household');
        }
    }

    public observeHousehold(): Observable<Household | string> {
        return this.subject.asObservable()
            .pipe(
                filter(household => household !== undefined)
            );
    }
}
