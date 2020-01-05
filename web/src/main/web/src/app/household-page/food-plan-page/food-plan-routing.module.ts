import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { FoodPlanPageComponent } from './food-plan-page.component';

const routes: Routes = [
  {
    path: '',
    component: FoodPlanPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class FoodPlanRoutingModule { }
