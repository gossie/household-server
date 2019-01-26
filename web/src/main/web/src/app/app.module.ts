import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './login-page/login-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { ShoppingListPageComponent } from './shopping-list-page/shopping-list-page.component';
import { CleaningPlanPageComponent } from './cleaning-plan-page/cleaning-plan-page.component';
import { FoodPlanPageComponent } from './food-plan-page/food-plan-page.component';
import { RecipesPageComponent } from './recipes-page/recipes-page.component';
import { ShoppingListGroupComponent } from './shopping-list-page/shopping-list-group/shopping-list-group.component';
import { ShoppingListItemComponent } from './shopping-list-page/shopping-list-group/shopping-list-item/shopping-list-item.component';
import { ChoreComponent } from './cleaning-plan-page/chore/chore.component';
import { RegistrationPageComponent } from './registration-page/registration-page.component';
import { SplashPageComponent } from './splash-page/splash-page.component';
import { CoverPageComponent } from './cover-page/cover-page.component';

@NgModule({
  declarations: [
    AppComponent,
    LoginPageComponent,
    HouseholdPageComponent,
    ShoppingListPageComponent,
    CleaningPlanPageComponent,
    FoodPlanPageComponent,
    RecipesPageComponent,
    ShoppingListGroupComponent,
    ShoppingListItemComponent,
    ChoreComponent,
    RegistrationPageComponent,
    SplashPageComponent,
    CoverPageComponent
  ],
  imports: [
    ReactiveFormsModule,
    HttpClientModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
