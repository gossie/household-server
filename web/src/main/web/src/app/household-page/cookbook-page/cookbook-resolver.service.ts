import { Injectable } from '@angular/core';
import {ActivatedRouteSnapshot, Resolve, RouterStateSnapshot} from "@angular/router";
import {Observable} from "rxjs/index";
import {Cookbook} from "./cookbook";
import {CookbookService} from "./cookbook.service";

@Injectable({
    providedIn: 'root'
})
export class CookbookResolverService implements Resolve<Cookbook> {

    constructor(private cookcookService: CookbookService) { }

    public resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Cookbook> {
        return this.cookcookService.determineCookbook(route.paramMap.get('url'));
    }
}
