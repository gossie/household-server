import { Component, Input, OnDestroy, OnInit } from '@angular/core';
import { HouseholdService } from '../household.service';
import { interval, Subscription } from 'rxjs/index';
import { UserService } from '../../user.service';
import { User } from '../../user';
import { CommonModule } from '@angular/common';
import { InvitationComponent } from 'src/app/common-elements/invitation/invitation.component';
import { Router, RouterModule } from '@angular/router';
import { Page } from 'src/app/page.enum';

@Component({
  selector: 'app-no-household',
  standalone: true,
  imports: [CommonModule, RouterModule, InvitationComponent],
  templateUrl: './no-household.component.html',
  styleUrls: ['./no-household.component.sass']
})
export class NoHouseholdComponent implements OnInit, OnDestroy {

    @Input()
    public user: User;

    public expanded = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private userService: UserService,
                private householdService: HouseholdService,
                private router: Router) { }

    public ngOnInit(): void {
        this.subscriptions.push(interval(10000)
            .subscribe(() => this.userService.updateUser()));
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    public createHousehold(): void {
        this.householdService.createHousehold()
            .subscribe(() => this.router.navigateByUrl(`/${Page.Household}/${Page.Cover}`));
    }

    public toggleNavbar(): void {
        this.expanded = !this.expanded;
    }

    public logout(): void {
        this.userService.logout();
    }
}
