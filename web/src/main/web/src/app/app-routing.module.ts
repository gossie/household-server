import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { Page } from './page.enum';

const routes: Routes = [{
        path: '',
        redirectTo: `${Page.Cover}`,
        pathMatch: 'full'
    },
    {
        path: Page.Cover,
        loadChildren: () => import('./household-page/cover-page/cover.module').then(m => m.CoverModule),
    },
    {
        path: Page.ShoppingList,
        loadChildren: () => import('./household-page/shopping-list-page/shopping-list.module').then(m => m.ShoppingListModule),
    },
    {
        path: Page.CleaningPlan,
        loadChildren: () => import('./household-page/cleaning-plan-page/cleaning-plan.module').then(m => m.CleaningPlanModule),
    },
    {
        path: Page.FoodPlan,
        loadChildren: () => import('./household-page/food-plan-page/food-plan.module').then(m => m.FoodPlanModule),
    },
    {
        path: Page.Cookbook,
        loadChildren: () => import('./household-page/cookbook-page/cookbook.module').then(m => m.CookbookModule),
    }
];

@NgModule({
    imports: [RouterModule.forRoot(routes, {useHash: true})],
    exports: [RouterModule]
})
export class AppRoutingModule { }
