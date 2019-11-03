import { Injectable } from '@angular/core';
import { BehaviorSubject, Observable, Subject } from 'rxjs/index';
import { Household } from './household';
import { UserService } from '../user.service';
import { HttpClient } from '@angular/common/http';
import { AbstractNetworkService } from '../abstract-network.service';
import { User } from '../user';
import { tap } from 'rxjs/internal/operators';

@Injectable({
    providedIn: 'root'
})
export class HouseholdService extends AbstractNetworkService {

    private subject: Subject<Household> = new BehaviorSubject(null);

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public createHousehold(): Observable<Household> {
        const url: string = this.determineUrl(this.userService.getUser(), 'create');
        return this.httpClient.post<Household>(url, null, {
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
            return this.httpClient.get<Household>(url, {
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
        if (url !== undefined) {
            this.httpClient.get<Household>(url, {
                headers: {
                    Accept: 'application/vnd.household.v1+json'
                }
            }).subscribe((household: Household) => this.subject.next(household));
        } else {
            this.subject.next(null);
        }
    }

    public observeHousehold(): Observable<Household> {
        return this.subject.asObservable();
    }
}
