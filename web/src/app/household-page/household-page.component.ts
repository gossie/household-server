import { Component, OnDestroy, OnInit } from '@angular/core';
import { Household } from './household';
import { Router, NavigationEnd, RouterModule, ActivatedRoute } from '@angular/router';
import { filter, tap } from 'rxjs/operators';
import { Subscription } from 'rxjs/index';
import { UserService } from '../user.service';
import { User } from '../user';
import { CommonElementsModule } from '../common-elements/common-elements.module';
import { NoHouseholdComponent } from './no-household/no-household.component';
import { CommonModule } from '@angular/common';
import { LoadingComponent } from './loading/loading.component';

@Component({
    selector: 'app-household-page',
    standalone: true,
    imports: [
        CommonModule,
        RouterModule,
        CommonElementsModule,
        NoHouseholdComponent,
        LoadingComponent
    ],
    templateUrl: './household-page.component.html',
    styleUrls: ['./household-page.component.sass']
})
export class HouseholdPageComponent implements OnInit, OnDestroy {

    public userHasNoHousehold = false;
    public user: User;
    public household: Household;
    public expanded = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private userService: UserService,
                private router: Router,
                private activatedRoute: ActivatedRoute) { }

    public ngOnInit(): void {
        this.activatedRoute.data
                .pipe(
                    tap(({household}) => {
                        console.debug('checking if household exists', household)
                        this.userHasNoHousehold = household === 'no-household';
                    })
                )
                .subscribe(({household, user}) => {
                    console.debug('got user and household', user, household);
                    this.household = household;
                    this.user = user;
                });
        this.observeRouter();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
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
        this.userService.logout();
    }
}
