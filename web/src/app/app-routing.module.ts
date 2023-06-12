import { inject, NgModule } from '@angular/core';
import { Routes, RouterModule, Router } from '@angular/router';
import { LoginPageComponent } from './login-page/login-page.component';
import { Page } from './page.enum';
import { RegistrationPageComponent } from './registration-page/registration-page.component';

const jwtGuard = () => {
    const router = inject(Router);
    const jwt = localStorage.getItem('jwt');
    if (jwt) {
        if (router.url === '' || router.url === '/') {
            return router.parseUrl(`/${Page.Household}/${Page.Cover}`)
        }
        return true;
    } else {
        return router.parseUrl(`/${Page.Login}`)
    }
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
        loadComponent: () => import('./household-page/household-page.component').then(m => m.HouseholdPageComponent),
        children: [
            {
                path: Page.Cover,
                canActivate: [jwtGuard],
                loadChildren: () => import('./household-page/cover-page/cover.module').then(m => m.CoverModule),
                pathMatch: 'full'
            },
            {
                path: Page.ShoppingList,
                canActivate: [jwtGuard],
                loadChildren: () => import('./household-page/shopping-list-page/shopping-list.module').then(m => m.ShoppingListModule),
                pathMatch: 'full'
            },
            {
                path: Page.CleaningPlan,
                canActivate: [jwtGuard],
                loadChildren: () => import('./household-page/cleaning-plan-page/cleaning-plan.module').then(m => m.CleaningPlanModule),
                pathMatch: 'full'
            },
            {
                path: Page.FoodPlan,
                canActivate: [jwtGuard],
                loadChildren: () => import('./household-page/food-plan-page/food-plan.module').then(m => m.FoodPlanModule),
                pathMatch: 'full'
            },
            {
                path: Page.Cookbook,
                canActivate: [jwtGuard],
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
