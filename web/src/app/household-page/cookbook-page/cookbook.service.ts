import { Injectable } from '@angular/core';
import { Cookbook } from './cookbook';
import { UserService } from '../../user.service';
import { HttpClient } from '@angular/common/http';
import { Observable, Subject } from 'rxjs/index';
import { AbstractNetworkService } from '../../abstract-network.service';
import { Household } from '../household';
import { Recipe } from './recipe/recipe';
import { filter, tap } from 'rxjs/internal/operators';
import { ObjectUtils } from '../../object.utils';
import { environment } from 'src/environments/environment';

@Injectable({
    providedIn: 'root',
})
export class CookbookService extends AbstractNetworkService {

    private cookbookSubject: Subject<Cookbook> = new Subject();
    private recipeSubject: Subject<Recipe> = new Subject();

    constructor(private httpClient: HttpClient) {
        super();
    }

    public determineCookbook(household: Household): Observable<Cookbook> {
        const url: string = this.determineUrl(household, 'cookbook');
        return this.httpClient.get<Cookbook>(`${environment.apiUrl}${url}`, {
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
        return this.httpClient.get<Recipe>(`${environment.apiUrl}${url}`, {
            headers: {
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public deleteRecipe(recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(recipe, 'self');
        return this.httpClient.delete<Cookbook>(`${environment.apiUrl}${url}`, {
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
        return this.httpClient.post<Cookbook>(`${environment.apiUrl}${url}`, recipe, {
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
        return this.httpClient.put<Cookbook>(`${environment.apiUrl}${url}`, recipe, {
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
        this.httpClient.get<Recipe>(`${environment.apiUrl}${url}`, {
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
