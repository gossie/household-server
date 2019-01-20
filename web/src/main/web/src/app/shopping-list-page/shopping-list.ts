import {Model} from "../model";
import {ShoppingListGroup} from "./shopping-list-group/shopping-list-group";

export interface ShoppingList extends Model {

    shoppingListGroups: Array<ShoppingListGroup>;

}
