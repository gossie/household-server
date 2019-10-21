import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import {Observable, of, Subject} from "rxjs/index";
import { Household } from "../household";
import {Recipe} from "./recipe/recipe";
import {tap} from "rxjs/internal/operators";

@Injectable()
export class CookbookServiceMock {

    private subject: Subject<Cookbook> = new Subject();

    constructor() {}

    public determineCookbook(household: Household): Observable<Cookbook> {
        return of({
            recipes: [
                {
                    name: 'Curry'
                },
                {
                    name: 'Chili con carne'
                },
                {
                    name: 'Käsekuchen'
                },
                {
                    name: 'chili sin carne'
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
        return this.subject.asObservable();
    }

    public createRecipe(cookbook: Cookbook, recipe: Recipe): Observable<Cookbook> {
        return of({
            recipes: [recipe]
        }).pipe(
            tap((cookbook: Cookbook) => this.subject.next(cookbook))
        );
    }

    public editRecipe(recipe: Recipe): Observable<Cookbook> {
        return of({
            recipes: [recipe]
        }).pipe(
            tap((cookbook: Cookbook) => this.subject.next(cookbook))
        );
    }

    public deleteRecipe(recipe: Recipe): Observable<Cookbook> {
        return of({
            recipes: []
        }).pipe(
            tap((cookbook: Cookbook) => this.subject.next(cookbook))
        );
    }

    public observeRecipe(): Observable<Recipe> {
        return of();
    }
}
