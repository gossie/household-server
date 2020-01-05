import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CoverPageComponent } from './cover-page.component';

const routes: Routes = [
  {
    path: '',
    component: CoverPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoverRoutingModule { }
