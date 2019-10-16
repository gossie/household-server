import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CommonElementsModule } from '../../common-elements/common-elements.module';
import { CookbookRoutingModule } from './cookbook-routing.module';
import { CookbookPageComponent } from './cookbook-page.component';
import { RecipeComponent } from './recipe/recipe.component';
import { IngredientComponent } from './recipe/ingredient/ingredient.component';
import { AddRecipeComponent } from './add-recipe/add-recipe.component';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        CommonElementsModule,
        CookbookRoutingModule
    ],
    declarations: [
        CookbookPageComponent,
        RecipeComponent,
        IngredientComponent,
        AddRecipeComponent
    ]
})
export class CookbookModule { }
