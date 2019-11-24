import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
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
        CommonElementsModule,
        FoodPlanRoutingModule
    ],
    declarations: [
        FoodPlanPageComponent,
        MealComponent
    ]
})
export class FoodPlanModule { }
