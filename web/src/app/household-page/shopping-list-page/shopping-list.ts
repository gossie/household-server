import { ShoppingListGroup } from "./shopping-list-group/shopping-list-group";
import { Model } from "../../model";

export interface ShoppingList extends Model {

    shoppingListGroups: Array<ShoppingListGroup>;

}
