import {Component, Input, OnInit} from '@angular/core';
import {ShoppingListGroup} from "./shopping-list-group";

@Component({
  selector: 'app-shopping-list-group',
  templateUrl: './shopping-list-group.component.html',
  styleUrls: ['./shopping-list-group.component.css']
})
export class ShoppingListGroupComponent implements OnInit {

    @Input()
    public shoppingListGroup: ShoppingListGroup;

    constructor() { }

    public ngOnInit(): void {
    }

}
