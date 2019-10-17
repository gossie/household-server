import { Model } from "../../model";
import { Meal } from "./meal/meal";

export interface FoodPlan extends Model {
    meals: {
        monday: Meal;
        tuesday: Meal;
        wednesday: Meal;
        thursday: Meal;
        friday: Meal;
        saturday: Meal;
        sunday: Meal;
    };
}
