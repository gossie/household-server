import { ShoppingListItem } from "./shopping-list-item/shopping-list-item";
import { Model } from "../../../model";

export interface ShoppingListGroup extends Model {
    name: string;
    shoppingListItems: Array<ShoppingListItem>
}
