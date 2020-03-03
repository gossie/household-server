import { Injectable } from '@angular/core';
import { ShoppingList } from './shopping-list';
import { Observable, of } from 'rxjs/index';
import { Household } from '../household';
import { ShoppingListItem } from './shopping-list-group/shopping-list-item/shopping-list-item';

@Injectable()
export class ShoppingListServiceMock {

    constructor() { }

    public determineShoppingList(household: Household): Observable<ShoppingList> {
        return of({
            shoppingListGroups: [],
            links: []
        });
    }

    public addShoppingListGroup(shoppingList: ShoppingList, name: string): Observable<ShoppingList> {
        return of();
    }

    public observeShoppingList(): Observable<ShoppingList> {
        return of({
            shoppingListGroups: [],
            links: []
        });
    }

    public toggleShoppingListItem(shoppingListItem: ShoppingListItem): Observable<ShoppingList> {
        return of({
            shoppingListGroups: [],
            links: []
        });
    }

    public editShoppingListItem(shoppingListItem: ShoppingListItem): Observable<ShoppingList> {
        return of({
            shoppingListGroups: [],
            links: []
        });
    }

}
