import { Injectable } from '@angular/core';
import { Cookbook } from './cookbook';
import { UserService } from '../../user.service';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable, Subject } from 'rxjs/index';
import { AbstractNetworkService } from '../../abstract-network.service';
import { Household } from '../household';
import { Recipe } from './recipe/recipe';
import { filter, tap } from 'rxjs/internal/operators';
import { ObjectUtils } from '../../object.utils';

@Injectable({
    providedIn: 'root',
})
export class CookbookService extends AbstractNetworkService {

    private cookbookSubject: Subject<Cookbook> = new BehaviorSubject(null);
    private recipeSubject: Subject<Recipe> = new BehaviorSubject(null);

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineCookbook(household: Household): Observable<Cookbook> {
        const url: string = this.determineUrl(household, 'cookbook');
        return this.httpClient.get<Cookbook>(url, {
            headers: {
                Accept: 'application/vnd.household.min.v1+json'
            }
        })
        .pipe(
            tap((cookbook: Cookbook) => this.cookbookSubject.next(cookbook))
        );
    }

    public determineRecipe(minRecipe: Recipe): Observable<Recipe> {
        const url: string = this.determineUrl(minRecipe, 'self');
        return this.httpClient.get<Recipe>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public deleteRecipe(recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(recipe, 'self');
        return this.httpClient.delete<Cookbook>(url, {
            headers: {
                Accept: 'application/vnd.household.min.v1+json'
            }
        })
        .pipe(
            tap((cookbook: Cookbook) => this.cookbookSubject.next(cookbook))
        );
    }

    public createRecipe(cookbook: Cookbook, recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(cookbook, 'create');
        return this.httpClient.post<Cookbook>(url, recipe, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.min.v1+json'
            }
        })
        .pipe(
            tap((newCookbook: Cookbook) => this.cookbookSubject.next(newCookbook))
        );
    }

    public editRecipe(recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(recipe, 'self');
        return this.httpClient.put<Cookbook>(url, recipe, {
            headers: {
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.min.v1+json'
            }
        })
        .pipe(
            tap((cookbook: Cookbook) => this.cookbookSubject.next(cookbook))
        );
    }

    public determineRecipeByUrl(url: string): void {
        this.httpClient.get<Recipe>(url, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        }).subscribe((recipe: Recipe) => {
            this.recipeSubject.next(recipe);
        });
    }

    public observeCookbook(): Observable<Cookbook> {
        return this.cookbookSubject.asObservable()
            .pipe(
                filter((cookbook: Cookbook) => ObjectUtils.isObject(cookbook))
            );
    }

    public observeRecipe(): Observable<Recipe> {
        return this.recipeSubject.asObservable()
            .pipe(
                filter((recipe: Recipe) => ObjectUtils.isObject(recipe))
            );
    }
}
