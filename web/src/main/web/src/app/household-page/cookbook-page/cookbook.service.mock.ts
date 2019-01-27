import { Injectable } from '@angular/core';
import { Cookbook } from "./cookbook";
import { Observable, of } from "rxjs/index";

@Injectable()
export class CookbookService {

    constructor() {}

    public determineCookbook(url: string): Observable<Cookbook> {
        return of();
    }
}
