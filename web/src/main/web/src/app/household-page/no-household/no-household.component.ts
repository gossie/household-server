import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { HouseholdService } from '../household.service';
import { interval, Subscription } from 'rxjs/index';
import { UserService } from '../../user.service';
import { User } from '../../user';

@Component({
  selector: 'app-no-household',
  templateUrl: './no-household.component.html',
  styleUrls: ['./no-household.component.sass']
})
export class NoHouseholdComponent implements OnInit, OnDestroy {

    @Input()
    public user: User;

    public expanded = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private userService: UserService,
                private householdService: HouseholdService) { }

    public ngOnInit(): void {
        this.subscriptions.push(interval(10000)
            .subscribe(() => this.userService.updateUser()));
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    public createHousehold(): void {
        this.householdService.createHousehold()
            .subscribe(() => {});
    }

    public toggleNavbar(): void {
        this.expanded = !this.expanded;
    }

    public logout(): void {
        console.debug('perform logout');
        this.userService.logout()
            .subscribe(
                () => location.href = 'login.html',
                () => location.href = 'login.html'
            );
    }
}
