import { Component, OnDestroy, OnInit } from '@angular/core';
import { Household } from './household';
import { Link } from "../model";
import { ActivatedRoute, Router, NavigationEnd } from "@angular/router";
import { filter } from 'rxjs/operators';
import { HouseholdService } from "./household.service";
import { Subscription } from "rxjs/index";
import { UserService } from "../user.service";
import { User } from "../splash-page/login-page/user";
import {UserData} from "../user-data";

@Component({
    selector: 'app-household-page',
    templateUrl: './household-page.component.html',
    styleUrls: ['./household-page.component.css']
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
        this.household = this.route.snapshot.data.household;
        this.userService.observeUserData().subscribe((userData: UserData) => {
            this.user = userData.user;
        });
        this.subscriptions.push(this.router.events
            .pipe(
                filter(evt => evt instanceof NavigationEnd)
            )
            .subscribe((evt: NavigationEnd) => {
                this.expanded = false;
            }));
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    public createHousehold(): void {
        this.householdService.createHousehold().subscribe((household: Household) => this.household = household);
    }

    public determineUrl(rel: string): string {
        return this.household.links.find((link: Link) => link.rel === rel).href;
    }

    public toggleNavbar(): void {
        this.expanded = !this.expanded;
    }
}
