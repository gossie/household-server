import {Model} from "../../../model";

export interface ShoppingListItem extends Model {
    name: string;
    selected: boolean;
}
