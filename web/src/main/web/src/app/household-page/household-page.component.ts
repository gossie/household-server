import { Component, OnDestroy, OnInit } from '@angular/core';
import { Household } from './household';
import { Router, NavigationEnd } from '@angular/router';
import { filter, tap } from 'rxjs/operators';
import { HouseholdService } from './household.service';
import { Subscription } from 'rxjs/index';
import { UserService } from '../user.service';
import { User } from '../user';

@Component({
    selector: 'app-household-page',
    templateUrl: './household-page.component.html',
    styleUrls: ['./household-page.component.sass']
})
export class HouseholdPageComponent implements OnInit, OnDestroy {

    public userHasNoHousehold = false;
    public household: Household;
    public user: User;
    public expanded = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private householdService: HouseholdService,
                private userService: UserService,
                private router: Router) { }

    public ngOnInit(): void {
        this.observeUser();
        this.observeHousehold();
        this.observeRouter();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUser(): void {
        this.subscriptions.push(this.userService.observeUser()
            .subscribe((user: User) => {
                this.user = user;
                this.householdService.updateHousehold(user);
            })
        );
        this.userService.determineCurrentUser().subscribe((user: User) => this.user = user);
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .pipe(
                tap((household: Household) => this.userHasNoHousehold = household === null)
            )
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
        this.userService.logout()
            .subscribe(
                () => location.href = 'login.html',
                () => location.href = 'login.html'
            );
    }
}
