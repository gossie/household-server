import { Injectable } from '@angular/core';
import { ShoppingList } from "./shopping-list";
import { Observable } from "rxjs/index";
import { HttpClient } from "@angular/common/http";
import { UserService } from "../user.service";
import { AbstractNetworkService } from "../abstract-network.service";
import { ShoppingListGroup } from "./shopping-list-group/shopping-list-group";
import { ShoppingListItem } from "./shopping-list-group/shopping-list-item/shopping-list-item";

@Injectable({
    providedIn: 'root'
})
export class ShoppingListService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineShoppingList(url: string): Observable<ShoppingList> {
        return this.httpClient.get<ShoppingList>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v2+json'
            }
        });
    }

    public addShoppingListGroup(shoppingList: ShoppingList, name: string): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingList, 'add');
        const body: ShoppingListGroup = {
            name: name,
            shoppingListItems: [],
            links: []
        };

        return this.httpClient.post<ShoppingList>(url, body, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v2+json',
                Accept: 'application/vnd.household.v2+json'
            }
        });
    }

    public deleteShoppingListGroup(shoppingListGroup: ShoppingListGroup): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListGroup, 'delete');
        return this.httpClient.delete<ShoppingList>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v2+json'
            }
        });
    }

    public addShoppingListItem(shoppingListGroup: ShoppingListGroup, name: string): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListGroup, 'add');
        const body: Array<ShoppingListItem> = [{
            name: name,
            links: []
        }];

        return this.httpClient.post<ShoppingList>(url, body, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v2+json',
                Accept: 'application/vnd.household.v2+json'
            }
        });
    }

}
