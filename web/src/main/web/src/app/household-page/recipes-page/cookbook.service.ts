import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import { UserService } from "../../user.service";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/index";
import { AbstractNetworkService } from "../../abstract-network.service";

@Injectable({
    providedIn: 'root'
})
export class CookbookService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineCookbook(url: string): Observable<Cookbook> {
        return this.httpClient.get<Cookbook>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }
}
