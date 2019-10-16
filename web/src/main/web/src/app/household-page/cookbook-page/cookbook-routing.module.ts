import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CookbookPageComponent } from './cookbook-page.component';

const routes: Routes = [
  {
    path: '',
    component: CookbookPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CookbookRoutingModule { }
