import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/index";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {Cookbook} from "./cookbook";

@Injectable({
  providedIn: 'root'
})
export class CookbookResolverService implements Resolve<Cookbook> {

    constructor(private userService: UserService,
                private httpClient: HttpClient) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cookbook> {
        return this.httpClient.get<Cookbook>(route.paramMap.get('url'), {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }
}
