import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import { UserService } from "../../user.service";
import { HttpClient } from "@angular/common/http";
import { Observable } from "rxjs/index";
import { AbstractNetworkService } from "../../abstract-network.service";
import { Household } from "../household";
import {Recipe} from "./recipe/recipe";

@Injectable({
    providedIn: 'root'
})
export class CookbookService extends AbstractNetworkService {

    constructor(private userService: UserService,
                private httpClient: HttpClient) {
        super();
    }

    public determineCookbook(household: Household): Observable<Cookbook> {
        const url: string = this.determineUrl(household, 'cookbook');
        return this.httpClient.get<Cookbook>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }

    public determineRecipe(minRecipe: Recipe): Observable<Recipe> {
        const url: string = this.determineUrl(minRecipe, 'self');
        return this.httpClient.get<Recipe>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.v1+json'
            }
        });
    }

    public deleteRecipe(recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(recipe, 'self');
        return this.httpClient.delete<Cookbook>(url, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }

    public createRecipe(cookbook: Cookbook, recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(cookbook, 'create');
        return this.httpClient.post<Cookbook>(url, recipe, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }

    public editRecipe(recipe: Recipe): Observable<Cookbook> {
        const url: string = this.determineUrl(recipe, 'self');
        return this.httpClient.put<Cookbook>(url, recipe, {
            headers: {
                Authorization: this.userService.getUserData().authData,
                'Content-Type': 'application/vnd.household.v1+json',
                Accept: 'application/vnd.household.min.v1+json'
            }
        });
    }
}
