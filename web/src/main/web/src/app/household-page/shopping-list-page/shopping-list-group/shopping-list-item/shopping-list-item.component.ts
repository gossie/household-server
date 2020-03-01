import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ShoppingListItem} from "./shopping-list-item";
import {ShoppingListService} from "../../shopping-list.service";
import {ShoppingList} from "../../shopping-list";

@Component({
    selector: 'app-shopping-list-item',
    templateUrl: './shopping-list-item.component.html',
    styleUrls: ['./shopping-list-item.component.sass']
})
export class ShoppingListItemComponent {
    
    @Input()
    public shoppingListItem: ShoppingListItem;
    @Output()
    public shoppingListEmitter: EventEmitter<ShoppingList> = new EventEmitter();

    public editMode = false;

    constructor(private shoppingListService: ShoppingListService) { }

    public toggleShoppingListItem(event: any): void {
        this.shoppingListService.toggleShoppingListItem(this.shoppingListItem)
            .subscribe((shoppingList: ShoppingList) => {
                this.shoppingListEmitter.emit(shoppingList);
            });
    }

    public enableEditMode(): void {
        this.editMode = true;
    }

}
