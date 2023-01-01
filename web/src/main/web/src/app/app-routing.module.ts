import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { Page } from './page.enum';
import { RegistrationPageComponent } from './registration-page/registration-page.component';

const routes: Routes = [{
        path: '',
        redirectTo: Page.Login,
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
        loadComponent: () => import('./household-page/household-page.component').then(m => m.HouseholdPageComponent),
        children: [
            {
                path: Page.Cover,
                loadChildren: () => import('./household-page/cover-page/cover.module').then(m => m.CoverModule),
                pathMatch: 'full'
            },
            {
                path: Page.ShoppingList,
                loadChildren: () => import('./household-page/shopping-list-page/shopping-list.module').then(m => m.ShoppingListModule),
                pathMatch: 'full'
            },
            {
                path: Page.CleaningPlan,
                loadChildren: () => import('./household-page/cleaning-plan-page/cleaning-plan.module').then(m => m.CleaningPlanModule),
                pathMatch: 'full'
            },
            {
                path: Page.FoodPlan,
                loadChildren: () => import('./household-page/food-plan-page/food-plan.module').then(m => m.FoodPlanModule),
                pathMatch: 'full'
            },
            {
                path: Page.Cookbook,
                loadChildren: () => import('./household-page/cookbook-page/cookbook.module').then(m => m.CookbookModule),
                pathMatch: 'full'
            }
        
        ]
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {
    useHash: true,
    relativeLinkResolution: 'legacy'
})],
    exports: [RouterModule]
})
export class AppRoutingModule { }
