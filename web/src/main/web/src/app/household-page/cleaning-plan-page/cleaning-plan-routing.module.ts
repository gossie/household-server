import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CleaningPlanPageComponent } from './cleaning-plan-page.component';

const routes: Routes = [
  {
    path: '',
    component: CleaningPlanPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CleaningPlanRoutingModule { }
