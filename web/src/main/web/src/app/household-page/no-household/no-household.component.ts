import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { HouseholdService } from "../household.service";
import { interval, Subscription } from "rxjs/index";
import { UserService } from "../../user.service";
import { User } from "../../splash-page/login-page/user";
import { Page } from "../../page.enum";
import { Router } from "@angular/router";

@Component({
  selector: 'app-no-household',
  templateUrl: './no-household.component.html',
  styleUrls: ['./no-household.component.css']
})
export class NoHouseholdComponent implements OnInit, OnDestroy {

    @Input()
    public user: User;

    private subscriptions: Array<Subscription> = [];

    constructor(private userService: UserService,
                private householdService: HouseholdService,
                private router: Router) { }

    public ngOnInit(): void {
        this.subscriptions.push(interval(5000)
            .subscribe(() => this.userService.updateUser()));
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    public createHousehold(): void {
        this.householdService.createHousehold()
            .subscribe(() => {});
    }

    public logout(): void {
        this.userService.setUserData(null);
        this.router.navigate([Page.Splash]);
    }
}
