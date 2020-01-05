import { Model } from "../../../../model";

export interface Ingredient extends Model {
    amount: number;
    unit: string;
    name: string;
}
