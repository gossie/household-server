import { Model } from "../../../model";
import { Ingredient } from "./ingredient/ingredient";

export interface Recipe extends Model {
    name: string;
    ingredients?: Array<Ingredient>;
    hidden?: boolean;
}
