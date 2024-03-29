import { Injectable } from '@angular/core';
import { ShoppingList } from './shopping-list';
import { Observable, Subject } from 'rxjs/index';
import { HttpClient } from '@angular/common/http';
import { ShoppingListGroup } from './shopping-list-group/shopping-list-group';
import { ShoppingListItem } from './shopping-list-group/shopping-list-item/shopping-list-item';
import { AbstractNetworkService } from '../../abstract-network.service';
import { Household } from '../household';
import { tap } from 'rxjs/internal/operators';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class ShoppingListService extends AbstractNetworkService {

    private subject: Subject<ShoppingList> = new Subject();

    constructor(private httpClient: HttpClient) {
        super();
    }

    public determineShoppingList(household: Household): Observable<ShoppingList> {
        const url: string = this.determineUrl(household, 'shoppingList');
        return this.httpClient.get<ShoppingList>(`${environment.apiUrl}${url}`, {
            headers: {
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public addShoppingListGroup(shoppingList: ShoppingList, name: string): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingList, 'add');
        const body: ShoppingListGroup = {
            name: name,
            shoppingListItems: [],
            links: []
        };

        return this.httpClient.post<ShoppingList>(`${environment.apiUrl}${url}`, body, {
            headers: {
                'Content-Type': 'application/vnd.household.v2+json',
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public deleteShoppingListGroup(shoppingListGroup: ShoppingListGroup): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListGroup, 'delete');
        return this.httpClient.delete<ShoppingList>(`${environment.apiUrl}${url}`, {
            headers: {
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public toggleShoppingListGroup(shoppingListGroup: ShoppingListGroup): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListGroup, 'toggle');
        return this.httpClient.put<ShoppingList>(`${environment.apiUrl}${url}`, null, {
            headers: {
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public clearShoppingListGroup(shoppingListGroup: ShoppingListGroup): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListGroup, 'clear');
        return this.httpClient.delete<ShoppingList>(`${environment.apiUrl}${url}`, {
            headers: {
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public addShoppingListItems(shoppingListGroup: ShoppingListGroup, names: Array<string>): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListGroup, 'add');
        const body: Array<ShoppingListItem> = names.map((name: string) => {
            return {
                name: name,
                selected: false,
                links: []
            }
        });

        return this.httpClient.post<ShoppingList>(`${environment.apiUrl}${url}`, body, {
            headers: {
                'Content-Type': 'application/vnd.household.v2+json',
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public toggleShoppingListItem(shoppingListItem: ShoppingListItem): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListItem, 'toggle');
        return this.httpClient.patch<ShoppingList>(`${environment.apiUrl}${url}`, null, {
            headers: {
                Accept: 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public editShoppingListItem(shoppingListItem: ShoppingListItem): Observable<ShoppingList> {
        const url: string = this.determineUrl(shoppingListItem, 'edit');
        return this.httpClient.put<ShoppingList>(`${environment.apiUrl}${url}`, shoppingListItem, {
            headers: {
                Accept: 'application/vnd.household.v2+json',
                'Content-Type': 'application/vnd.household.v2+json'
            }
        })
        .pipe(
            tap((shoppingList: ShoppingList) => this.subject.next(shoppingList))
        );
    }

    public observeShoppingList(): Observable<ShoppingList> {
        return this.subject.asObservable();
    }

}
