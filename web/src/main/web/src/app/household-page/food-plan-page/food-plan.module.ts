import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CommonElementsModule } from '../../common-elements/common-elements.module';
import { FoodPlanRoutingModule } from './food-plan-routing.module';
import { FoodPlanPageComponent } from './food-plan-page.component';
import { FoodPlanService } from './food-plan.service';
import { MealComponent } from './meal/meal.component';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientXsrfModule.withOptions({
            cookieName: 'XSRF-TOKEN',
            headerName: 'XSRF-TOKEN'
        }),
        CommonElementsModule,
        FoodPlanRoutingModule
    ],
    declarations: [
        FoodPlanPageComponent,
        MealComponent
    ],
    providers: [
        FoodPlanService
    ]
})
export class FoodPlanModule { }
