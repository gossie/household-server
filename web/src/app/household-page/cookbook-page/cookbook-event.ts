import { CookbookAction } from "./cookbook-action.enum";
import { Recipe } from "./recipe/recipe";

export interface CookbookEvent {
    action: CookbookAction,
    recipe: Recipe
}
