import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LoginPageComponent } from './splash-page/login-page/login-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { CleaningPlanPageComponent } from './household-page/cleaning-plan-page/cleaning-plan-page.component';
import { FoodPlanPageComponent } from './household-page/food-plan-page/food-plan-page.component';
import { RecipesPageComponent } from './household-page/recipes-page/recipes-page.component';
import { ChoreComponent } from './household-page/cleaning-plan-page/chore/chore.component';
import { RegistrationPageComponent } from './splash-page/registration-page/registration-page.component';
import { SplashPageComponent } from './splash-page/splash-page.component';
import { CoverPageComponent } from './household-page/cover-page/cover-page.component';
import { InvitationComponent } from './household-page/invitation/invitation.component';
import { ShoppingListPageComponent } from "./household-page/shopping-list-page/shopping-list-page.component";
import { ShoppingListGroupComponent}  from "./household-page/shopping-list-page/shopping-list-group/shopping-list-group.component";
import { ShoppingListItemComponent } from "./household-page/shopping-list-page/shopping-list-group/shopping-list-item/shopping-list-item.component";

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
    CoverPageComponent,
    InvitationComponent
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
