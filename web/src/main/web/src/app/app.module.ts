import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { CleaningPlanPageComponent } from './household-page/cleaning-plan-page/cleaning-plan-page.component';
import { FoodPlanPageComponent } from './household-page/food-plan-page/food-plan-page.component';
import { CookbookPageComponent } from './household-page/cookbook-page/cookbook-page.component';
import { ChoreComponent } from './household-page/cleaning-plan-page/chore/chore.component';
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
import { DatePipe } from './household-page/cleaning-plan-page/chore/date.pipe';
import { LoadingInterceptor } from "./household-page/loading.interceptor";
import { LoadingComponent } from './household-page/loading/loading.component';
import { CheckboxComponent } from './common-elements/checkbox/checkbox.component';
import { DeleteInterceptor } from "./household-page/delete.interceptor";
import { UndoHintComponent } from './common-elements/undo-hint/undo-hint.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";
import { ChangePasswordComponent } from './household-page/cover-page/change-password/change-password.component';
import { ProgressBarComponent } from './household-page/shopping-list-page/shopping-list-group/progress-bar/progress-bar.component';

@NgModule({
    declarations: [
        AppComponent,
        HouseholdPageComponent,
        ShoppingListPageComponent,
        CleaningPlanPageComponent,
        FoodPlanPageComponent,
        CookbookPageComponent,
        ShoppingListGroupComponent,
        ShoppingListItemComponent,
        ChoreComponent,
        CoverPageComponent,
        InvitationComponent,
        MealComponent,
        RecipeComponent,
        IngredientComponent,
        AddRecipeComponent,
        SelectRecipeComponent,
        NoHouseholdComponent,
        DatePipe,
        LoadingComponent,
        CheckboxComponent,
        UndoHintComponent,
        ChangePasswordComponent,
        ProgressBarComponent
    ],
    imports: [
        ReactiveFormsModule,
        HttpClientModule,
        BrowserModule,
        BrowserAnimationsModule,
        AppRoutingModule
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: DeleteInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
