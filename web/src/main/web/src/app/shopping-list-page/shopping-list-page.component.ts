import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {ShoppingList} from "./shopping-list";

@Component({
  selector: 'app-shopping-list-page',
  templateUrl: './shopping-list-page.component.html',
  styleUrls: ['./shopping-list-page.component.css']
})
export class ShoppingListPageComponent implements OnInit {

    public shoppingList: ShoppingList;

    constructor(private route: ActivatedRoute) { }

    public ngOnInit() {
        this.shoppingList = this.route.snapshot.data.shoppingList;
        console.log('this.shoppingList', this.shoppingList);
    }

}
