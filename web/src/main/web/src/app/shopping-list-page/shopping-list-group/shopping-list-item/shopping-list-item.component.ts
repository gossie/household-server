import {Component, Input, OnInit} from '@angular/core';
import {ShoppingListItem} from "./shopping-list-item";

@Component({
    selector: 'app-shopping-list-item',
    templateUrl: './shopping-list-item.component.html',
    styleUrls: ['./shopping-list-item.component.css']
})
export class ShoppingListItemComponent implements OnInit {

    @Input()
    public shoppingListItem: ShoppingListItem;

    constructor() { }

    ngOnInit() {
    }

}
