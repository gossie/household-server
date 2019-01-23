import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShoppingListItem} from "./shopping-list-item";
import {ShoppingListService} from "../../shopping-list.service";
import {ShoppingList} from "../../shopping-list";

@Component({
    selector: 'app-shopping-list-item',
    templateUrl: './shopping-list-item.component.html',
    styleUrls: ['./shopping-list-item.component.css']
})
export class ShoppingListItemComponent implements OnInit {

    @Input()
    public shoppingListItem: ShoppingListItem;
    @Output()
    public shoppingListEmitter: EventEmitter<ShoppingList> = new EventEmitter();

    constructor(private shoppingListService: ShoppingListService) { }

    ngOnInit() {
    }

    public toggleShoppingListItem(): void {
        this.shoppingListService.toggleShoppingListItem(this.shoppingListItem)
            .subscribe((shoppingList: ShoppingList) => {
                this.shoppingListEmitter.emit(shoppingList);
            });
    }

}
