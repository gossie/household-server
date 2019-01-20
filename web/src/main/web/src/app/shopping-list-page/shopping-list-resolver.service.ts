import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import { ShoppingList } from "./shopping-list";
import {interval, Observable} from "rxjs/index";
import {HttpClient} from "@angular/common/http";
import {UserService} from "../user.service";
import {ShoppingListService} from "./shopping-list.service";

@Injectable({
  providedIn: 'root'
})
export class ShoppingListResolverService implements Resolve<ShoppingList> {

    constructor(private shoppingListService: ShoppingListService) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<ShoppingList> {
        return this.shoppingListService.determineShoppingList(route.paramMap.get('url'));
    }
}
