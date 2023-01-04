import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { ShoppingListPageComponent } from './shopping-list-page.component';

const routes: Routes = [
  {
    path: '',
    component: ShoppingListPageComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class ShoppingListRoutingModule { }
