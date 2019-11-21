import { Meal } from './meal/meal';
import { Model } from '../../model';

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
