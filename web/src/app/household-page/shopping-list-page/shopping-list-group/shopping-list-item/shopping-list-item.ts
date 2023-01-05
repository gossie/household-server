import { Model } from '../../../../model';

export interface ShoppingListItem extends Model {
    name: string;
    selected: boolean;
    image?: string;
    hidden?: boolean;
}
