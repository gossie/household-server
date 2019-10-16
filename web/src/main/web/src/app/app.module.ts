import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { FormsModule }   from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { CommonElementsModule } from './common-elements/common-elements.module';
import { AppComponent } from './app.component';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { HouseholdPageComponent } from './household-page/household-page.component';
import { FoodPlanPageComponent } from './household-page/food-plan-page/food-plan-page.component';
import { CookbookPageComponent } from './household-page/cookbook-page/cookbook-page.component';
import { MealComponent } from './household-page/food-plan-page/meal/meal.component';
import { RecipeComponent } from './household-page/cookbook-page/recipe/recipe.component';
import { IngredientComponent } from './household-page/cookbook-page/recipe/ingredient/ingredient.component';
import { AddRecipeComponent } from './household-page/cookbook-page/add-recipe/add-recipe.component';
import { SelectRecipeComponent } from './household-page/food-plan-page/select-recipe/select-recipe.component';
import { NoHouseholdComponent } from './household-page/no-household/no-household.component';
import { LoadingInterceptor } from "./household-page/loading.interceptor";
import { LoadingComponent } from './household-page/loading/loading.component';
import { CheckboxComponent } from './common-elements/checkbox/checkbox.component';
import { DeleteInterceptor } from "./household-page/delete.interceptor";
import { UndoHintComponent } from './common-elements/undo-hint/undo-hint.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

@NgModule({
    declarations: [
        AppComponent,
        HouseholdPageComponent,
        FoodPlanPageComponent,
        CookbookPageComponent,
        MealComponent,
        RecipeComponent,
        IngredientComponent,
        AddRecipeComponent,
        SelectRecipeComponent,
        NoHouseholdComponent,
        LoadingComponent
    ],
    imports: [
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        BrowserModule,
        BrowserAnimationsModule,
        CommonElementsModule,
        AppRoutingModule
    ],
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true },
        { provide: HTTP_INTERCEPTORS, useClass: DeleteInterceptor, multi: true }
    ],
    bootstrap: [AppComponent]
})
export class AppModule { }
