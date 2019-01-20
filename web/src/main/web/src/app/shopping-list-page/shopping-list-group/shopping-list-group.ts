import {Model} from "../../model";
import {ShoppingListItem} from "./shopping-list-item/shopping-list-item";

export interface ShoppingListGroup extends Model {
    name: string;
    shoppingListItems: Array<ShoppingListItem>
}
