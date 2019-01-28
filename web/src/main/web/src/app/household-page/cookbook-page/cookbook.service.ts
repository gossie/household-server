import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import { UserService } from "../../user.service";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/index";
import { AbstractNetworkService } from "../../abstract-network.service";
import { Household } from "../household";

@Injectable({
    providedIn: 'root'
})
export class CookbookService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineCookbook(household: Household): Observable<Cookbook> {
        const url: string = this.determineUrl(household, 'cookbook');
        return this.httpClient.get<Cookbook>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }
}
