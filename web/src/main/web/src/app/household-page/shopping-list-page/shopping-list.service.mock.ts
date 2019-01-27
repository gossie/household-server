import { Injectable } from '@angular/core';
import { ShoppingList } from "./shopping-list";
import { Observable, of } from "rxjs/index";

@Injectable({
    providedIn: 'root'
})
export class ShoppingListServiceMock {

    constructor() { }

    public determineShoppingList(url: string): Observable<ShoppingList> {
        return of();
    }

    public addShoppingListGroup(shoppingList: ShoppingList, name: string): Observable<ShoppingList> {
        return of();
    }

}
