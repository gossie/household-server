import { Injectable } from '@angular/core';
import {BehaviorSubject, Observable, Subject} from "rxjs/index";
import { Household } from "./household";
import { UserService } from "../user.service";
import { HttpClient } from "@angular/common/http";
import { AbstractNetworkService } from "../abstract-network.service";
import {UserData} from "../user-data";
import {tap} from "rxjs/internal/operators";

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
        const url: string = this.determineUrl(this.userService.getUserData().user, 'create')
        return this.httpClient.post<Household>(url, null, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        }).pipe(
            tap((household: Household) => this.subject.next(household))
        );
    }

    public determineHousehold(): Observable<Household> {
        const userData: UserData = this.userService.getUserData();
        const url: string = this.determineUrl(userData.user, 'household');
        if (url !== undefined) {
            return this.httpClient.get<Household>(url, {
                headers: {
                    Authorization: userData.authData,
                    Accept: 'application/vnd.household.v1+json'
                }
            }).pipe(
                tap((household: Household) => this.subject.next(household))
            );
        } else {
            return undefined;
        }
    }

    public observeHousehold(): Observable<Household> {
        return this.subject.asObservable();
    }
}
