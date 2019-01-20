import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import { ShoppingList } from "./shopping-list";
import {Observable} from "rxjs/index";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";

@Injectable({
  providedIn: 'root'
})
export class ShoppingListResolverService implements Resolve<ShoppingList> {

    constructor(private userService: UserService,
                private httpClient: HttpClient) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ShoppingList> {
        return this.httpClient.get<ShoppingList>(route.paramMap.get('url'), {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v2+json'
            }
        });
    }
}
