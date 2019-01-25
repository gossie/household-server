import { Injectable } from '@angular/core';
import { Observable } from "rxjs/index";
import { Household } from "./household";
import { UserService } from "../user.service";
import { HttpClient } from "@angular/common/http";
import { AbstractNetworkService } from "../abstract-network.service";
import {User} from "../login-page/user";

@Injectable({
    providedIn: 'root'
})
export class HouseholdService extends AbstractNetworkService {

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
        });
    }
}
