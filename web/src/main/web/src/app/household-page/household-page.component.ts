import { Component, OnDestroy, OnInit } from '@angular/core';
import { Household } from './household';
import { ActivatedRoute, Router, NavigationEnd } from "@angular/router";
import { filter } from 'rxjs/operators';
import { HouseholdService } from "./household.service";
import { Subscription } from "rxjs/index";
import { UserService } from "../user.service";
import { User } from "../user";
import { UserData } from "../user-data";
import { Page } from "../page.enum";

@Component({
    selector: 'app-household-page',
    templateUrl: './household-page.component.html',
    styleUrls: ['./household-page.component.sass']
})
export class HouseholdPageComponent implements OnInit, OnDestroy {

    public household: Household;
    public user: User;
    public expanded: boolean = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private householdService: HouseholdService,
                private userService: UserService,
                private router: Router,
                private route: ActivatedRoute) { }

    public ngOnInit(): void {
        this.observeUser();
        this.observeHousehold();
        this.observeRouter();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUser(): void {
        this.subscriptions.push(this.userService.observeUserData()
            .subscribe((userData: UserData) => {
                this.user = userData.user;
                this.householdService.updateHousehold(userData);
            })
        );
    }

    private observeHousehold(): void {
        this.household = this.route.snapshot.data.household;
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => this.household = household)
        );
    }

    private observeRouter(): void {
        this.subscriptions.push(this.router.events
            .pipe(
                filter(evt => evt instanceof NavigationEnd)
            )
            .subscribe(() => this.expanded = false)
        );
    }

    public toggleNavbar(): void {
        this.expanded = !this.expanded;
    }

    public logout(): void {
        this.userService.setUserData(null);
        this.router.navigate([Page.Splash]);
    }
}
