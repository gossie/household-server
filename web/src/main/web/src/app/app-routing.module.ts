import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { Page } from './page.enum';
import { CleaningPlanPageComponent } from './household-page/cleaning-plan-page/cleaning-plan-page.component';
import { FoodPlanPageComponent } from './household-page/food-plan-page/food-plan-page.component';
import { CookbookPageComponent } from './household-page/cookbook-page/cookbook-page.component';
import { HouseholdResolverService } from './household-page/household-resolver.service';
import { CoverPageComponent } from "./household-page/cover-page/cover-page.component";
import { ShoppingListPageComponent } from "./household-page/shopping-list-page/shopping-list-page.component";

const routes: Routes = [{
    path: '',
    redirectTo: `/${Page.Household}/(inner:${Page.Cover})`,
    pathMatch: 'full'
}, {
    path: Page.Household,
    component: HouseholdPageComponent,
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
            outlet: 'inner'
        },
        {
            path: Page.ShoppingList,
            component: ShoppingListPageComponent,
            outlet: 'inner'
        }, {
            path: Page.CleaningPlan,
            loadChildren: () => import('./household-page/cleaning-plan-page/cleaning-plan.module').then(m => m.CleaningPlanModule),
            outlet: 'inner'
        }, {
            path: Page.FoodPlan,
            component: FoodPlanPageComponent,
            outlet: 'inner'
        }, {
            path: Page.Cookbook,
            component: CookbookPageComponent,
            outlet: 'inner'
        }
    ]
}];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule { }
