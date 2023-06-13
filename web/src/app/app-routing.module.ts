import { inject, NgModule } from '@angular/core';
import { Routes, RouterModule, Router, NavigationEnd } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { Page } from './page.enum';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { Household } from './household-page/household';
import { CleaningPlanService } from './household-page/cleaning-plan-page/cleaning-plan.service';
import { filter, mergeMap, tap } from 'rxjs/operators';
import { HouseholdService } from './household-page/household.service';
import { UserService } from './user.service';
import { User } from './user';
import { ShoppingListService } from './household-page/shopping-list-page/shopping-list.service';
import { FoodPlanService } from './household-page/food-plan-page/food-plan.service';
import { CookbookService } from './household-page/cookbook-page/cookbook.service';

const jwtGuard = () => {
    console.debug('checking jwt');
    const router = inject(Router);
    const jwt = localStorage.getItem('jwt');
    return jwt ? true : router.parseUrl(`/${Page.Login}`);
}

const userResolver = () => {
    console.debug('determine user from server');
    return inject(UserService).determineCurrentUser();
}

const householdResolver = () => {
    console.debug('determine household from server');
    const userService = inject(HouseholdService);
    const householdService = inject(HouseholdService);
    return userResolver()
            .pipe(
                tap((user: User) => userService.updateHousehold(user)),
                mergeMap(() => householdService.observeHousehold())
            );
}

const shoppingListResolver = () => {
    console.debug('determine shopping list from server');
    const shoppingListService = inject(ShoppingListService);
    return householdResolver()
            .pipe(
                mergeMap((household: Household) => shoppingListService.determineShoppingList(household))
            )
}

const cleaningPlanResolver = () => {
    console.debug('determine cleaning plan from server');
    const cleaningPlanService = inject(CleaningPlanService);
    return householdResolver()
            .pipe(
                mergeMap((household: Household) => cleaningPlanService.determineCleaningPlan(household))
            )
}

const foodPlanResolver = () => {
    console.debug('determine food plan from server');
    const foodPlanService = inject(FoodPlanService);
    return householdResolver()
            .pipe(
                mergeMap((household: Household) => foodPlanService.determineFoodPlan(household))
            )
}

const cookbookResolver = () => {
    console.debug('determine cookbook from server');
    const cookbookService = inject(CookbookService);
    return householdResolver()
            .pipe(
                mergeMap((household: Household) => cookbookService.determineCookbook(household))
            )
}

const routes: Routes = [{
        path: '',
        canActivate: [jwtGuard],
        component: LoginPageComponent,
        pathMatch: 'full'
    },
    {
        path: Page.Login,
        component: LoginPageComponent
    },
    {
        path: Page.Register,
        component: RegistrationPageComponent
    },
    {
        path: Page.Household,
        canActivate: [jwtGuard],
        resolve: {
            user: userResolver,
            household: () => {
                console.debug('in householdResolver wrapper')
                const router = inject(Router);
                return householdResolver()
                    .pipe(
                        tap(household => {
                            console.debug('household resolved', household)
                            router.events
                                .pipe(
                                    filter(ev => ev instanceof NavigationEnd)
                                )
                                .subscribe(() => {
                                    console.debug('navigation end', household)
                                    if (household !== 'no-household' && router.url === `/${Page.Household}`) {
                                        router.navigateByUrl(`/${Page.Household}/${Page.Cover}`);
                                    }
                                })
                        })
                    )
            }
        },
        loadComponent: () => import('./household-page/household-page.component').then(m => m.HouseholdPageComponent),
        children: [
            {
                path: Page.Cover,
                canActivate: [jwtGuard],
                resolve: {
                    user: userResolver,
                    household: householdResolver
                },
                loadChildren: () => import('./household-page/cover-page/cover.module').then(m => m.CoverModule),
                pathMatch: 'full'
            },
            {
                path: Page.ShoppingList,
                canActivate: [jwtGuard],
                resolve: {
                    shoppingList: shoppingListResolver
                },
                loadChildren: () => import('./household-page/shopping-list-page/shopping-list.module').then(m => m.ShoppingListModule),
                pathMatch: 'full'
            },
            {
                path: Page.CleaningPlan,
                canActivate: [jwtGuard],
                resolve: {
                    cleaningPlan: cleaningPlanResolver
                },
                loadChildren: () => import('./household-page/cleaning-plan-page/cleaning-plan.module').then(m => m.CleaningPlanModule),
                pathMatch: 'full'
            },
            {
                path: Page.FoodPlan,
                canActivate: [jwtGuard],
                resolve: {
                    foodPlan: foodPlanResolver,
                    cookbook: cookbookResolver,
                    shoppingList: shoppingListResolver
                },
                loadChildren: () => import('./household-page/food-plan-page/food-plan.module').then(m => m.FoodPlanModule),
                pathMatch: 'full'
            },
            {
                path: Page.Cookbook,
                canActivate: [jwtGuard],
                resolve: {
                    cookbook: cookbookResolver
                },
                loadChildren: () => import('./household-page/cookbook-page/cookbook.module').then(m => m.CookbookModule),
                pathMatch: 'full'
            }
        
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {
    useHash: true
})],
    exports: [RouterModule]
})
export class AppRoutingModule { }
