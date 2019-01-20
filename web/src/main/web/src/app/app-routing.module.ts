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

const routes: Routes = [{
    path: '',
    redirectTo: '/login',
    pathMatch: 'full'
}, {
    path: Page.Login,
    component: LoginPageComponent
}, {
    path: Page.Household,
    component: HouseholdPageComponent,
    canActivate: [AuthGuardService],
    resolve: [
        HouseholdResolverService
    ],
    children: [
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
            path: Page.Recipes,
            component: RecipesPageComponent,
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
