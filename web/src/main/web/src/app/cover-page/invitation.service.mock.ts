import { Injectable } from '@angular/core';
import { Observable, of } from "rxjs/index";
import { ShoppingList } from "../shopping-list-page/shopping-list";

@Injectable()
export class InvitationServiceMock {

    constructor() { }

    public sendInvitation(): Observable<ShoppingList> {
        return of();
    }
}
