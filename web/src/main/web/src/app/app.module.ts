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
import { CookbookPageComponent } from './household-page/cookbook-page/cookbook-page.component';
import { ChoreComponent } from './household-page/cleaning-plan-page/chore/chore.component';
import { RegistrationPageComponent } from './splash-page/registration-page/registration-page.component';
import { SplashPageComponent } from './splash-page/splash-page.component';
import { CoverPageComponent } from './household-page/cover-page/cover-page.component';
import { InvitationComponent } from './household-page/invitation/invitation.component';
import { ShoppingListPageComponent } from "./household-page/shopping-list-page/shopping-list-page.component";
import { ShoppingListGroupComponent}  from "./household-page/shopping-list-page/shopping-list-group/shopping-list-group.component";
import { ShoppingListItemComponent } from "./household-page/shopping-list-page/shopping-list-group/shopping-list-item/shopping-list-item.component";
import { MealComponent } from './household-page/food-plan-page/meal/meal.component';
import { RecipeComponent } from './household-page/cookbook-page/recipe/recipe.component';
import { IngredientComponent } from './household-page/cookbook-page/recipe/ingredient/ingredient.component';
import { AddRecipeComponent } from './household-page/cookbook-page/add-recipe/add-recipe.component';
import { SelectRecipeComponent } from './household-page/food-plan-page/select-recipe/select-recipe.component';
import { NoHouseholdComponent } from './household-page/no-household/no-household.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginPageComponent,
        HouseholdPageComponent,
        ShoppingListPageComponent,
        CleaningPlanPageComponent,
        FoodPlanPageComponent,
        CookbookPageComponent,
        ShoppingListGroupComponent,
        ShoppingListItemComponent,
        ChoreComponent,
        RegistrationPageComponent,
        SplashPageComponent,
        CoverPageComponent,
        InvitationComponent,
        MealComponent,
        RecipeComponent,
        IngredientComponent,
        AddRecipeComponent,
        SelectRecipeComponent,
        NoHouseholdComponent
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
