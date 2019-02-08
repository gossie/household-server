import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import { Observable, of } from "rxjs/index";
import { Household } from "../household";

@Injectable()
export class CookbookServiceMock {

    constructor() {}

    public determineCookbook(household: Household): Observable<Cookbook> {
        return of({
            recipes: [
                {
                    name: 'Chili con carne'
                },
                {
                    name: 'Curry'
                },
                {
                    name: 'Käsekuchen'
                }
            ]
        });
    }

    public observeCookbook(): Observable<Cookbook> {
        return of({
            recipes: [
                {
                    name: 'Chili con carne'
                },
                {
                    name: 'Curry'
                },
                {
                    name: 'Käsekuchen'
                }
            ]
        });
    }
}
