import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { Page } from './page.enum';
import { AuthGuardService} from './auth-guard.service';
import { ShoppingListPageComponent } from './shopping-list-page/shopping-list-page.component';
import { CleaningPlanPageComponent } from './cleaning-plan-page/cleaning-plan-page.component';
import { FoodPlanPageComponent } from './food-plan-page/food-plan-page.component';
import { RecipesPageComponent } from './recipes-page/recipes-page.component';
import { HouseholdResolverService } from './household-page/household-resolver.service';
import { ShoppingListResolverService } from "./shopping-list-page/shopping-list-resolver.service";
import { CookbookResolverService } from "./recipes-page/cookbook-resolver.service";
import { FoodPlanResolverService } from "./food-plan-page/food-plan-resolver.service";
import { CleaningPlanResolverService } from "./cleaning-plan-page/cleaning-plan-resolver.service";
import { SplashPageComponent } from "./splash-page/splash-page.component";
import { RegistrationPageComponent } from "./registration-page/registration-page.component";
import {CoverPageComponent} from "./cover-page/cover-page.component";

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
            redirectTo: Page.Registration,
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
            path: Page.Cover,
            component: CoverPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner',
            resolve: {
                household: HouseholdResolverService
            }
        },
        {
            path: Page.ShoppingList,
            component: ShoppingListPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner',
            resolve: {
                shoppingList: ShoppingListResolverService
            }
        }, {
            path: Page.CleaningPlan,
            component: CleaningPlanPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner',
            resolve: {
                cleaningPlan: CleaningPlanResolverService
            }
        }, {
            path: Page.FoodPlan,
            component: FoodPlanPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner',
            resolve: {
                foodPlan: FoodPlanResolverService
            }
        }, {
            path: Page.Cookbook,
            component: RecipesPageComponent,
            canActivate: [AuthGuardService],
            outlet: 'inner',
            resolve: {
                cookbook: CookbookResolverService
            }
        }
    ]
}];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule { }
