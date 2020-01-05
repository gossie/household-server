import { Model } from "../../model";
import { Recipe } from "./recipe/recipe";

export interface Cookbook extends Model {
    recipes: Array<Recipe>;
}
