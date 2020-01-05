import { Recipe } from '../../cookbook-page/recipe/recipe';
import { Meal } from './meal';

export interface RecipeSelectionEvent {
    recipe: Recipe;
    meal: Meal;
}