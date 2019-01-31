import { Component, OnDestroy, OnInit } from '@angular/core';
import { Cookbook } from "./cookbook";
import { HouseholdService } from "../household.service";
import { Subscription } from "rxjs/index";
import { Household } from "../household";
import { CookbookService } from "./cookbook.service";

@Component({
    selector: 'app-cookbook-page',
    templateUrl: './cookbook-page.component.html',
    styleUrls: ['./cookbook-page.component.css']
})
export class CookbookPageComponent implements OnInit, OnDestroy {

    public cookbook: Cookbook;

    private subscriptions: Array<Subscription> = [];

    constructor(private householdService: HouseholdService,
                private cookbookService: CookbookService) { }

    public ngOnInit() {
        this.observeHousehold();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.cookbookService.determineCookbook(household)
                    .subscribe(this.handleCookbook.bind(this));
            }));
    }

    public handleCookbook(cookbook: Cookbook): void {
        this.cookbook = cookbook
    }
}
