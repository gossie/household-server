import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './splash-page/login-page/login-page.component';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { Page } from './page.enum';
import { AuthGuardService} from './auth-guard.service';
import { CleaningPlanPageComponent } from './household-page/cleaning-plan-page/cleaning-plan-page.component';
import { FoodPlanPageComponent } from './household-page/food-plan-page/food-plan-page.component';
import { CookbookPageComponent } from './household-page/cookbook-page/cookbook-page.component';
import { HouseholdResolverService } from './household-page/household-resolver.service';
import { SplashPageComponent } from "./splash-page/splash-page.component";
import { RegistrationPageComponent } from "./splash-page/registration-page/registration-page.component";
import { CoverPageComponent } from "./household-page/cover-page/cover-page.component";
import { ShoppingListPageComponent } from "./household-page/shopping-list-page/shopping-list-page.component";

const routes: Routes = [{
    path: '',
    redirectTo: '/splash',
    pathMatch: 'full'
}, {
    path: Page.Splash,
    component: SplashPageComponent,
    children: [
        {
            path: '',
            redirectTo: `/${Page.Splash}/(user:${Page.Registration})`,
            pathMatch: 'full'
        },
        {
            path: Page.Registration,
            component: RegistrationPageComponent,
            outlet: 'user'
        },
        {
            path: Page.Login,
            component: LoginPageComponent,
            outlet: 'user'
        }
    ]
}, {
    path: Page.Household,
    component: HouseholdPageComponent,
    canActivate: [AuthGuardService],
    resolve: {
        household: HouseholdResolverService
    },
    children: [
        {
            path: '',
            redirectTo: `/${Page.Household}/(inner:${Page.Cover})`,
            pathMatch: 'full'
        },
        {
            path: Page.Cover,
            component: CoverPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner'
        },
        {
            path: Page.ShoppingList,
            component: ShoppingListPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner'
        }, {
            path: Page.CleaningPlan,
            component: CleaningPlanPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner'
        }, {
            path: Page.FoodPlan,
            component: FoodPlanPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner'
        }, {
            path: Page.Cookbook,
            component: CookbookPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner'
        }
    ]
}];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule { }
