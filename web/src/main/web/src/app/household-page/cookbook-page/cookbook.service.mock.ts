import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import { Observable, of } from "rxjs/index";
import { Household } from "../household";
import {Recipe} from "./recipe/recipe";

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

    public determineRecipe(minRecipe: Recipe): Observable<Recipe> {
        return of({
            name: 'Chili con carne',
            ingredients: [{
                amount: 500,
                unit: 'g',
                name: 'Hack'
            }]
        })
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
