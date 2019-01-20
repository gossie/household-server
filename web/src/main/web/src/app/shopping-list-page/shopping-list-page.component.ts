import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { ShoppingList } from "./shopping-list";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ShoppingListService } from "./shopping-list.service";

@Component({
  selector: 'app-shopping-list-page',
  templateUrl: './shopping-list-page.component.html',
  styleUrls: ['./shopping-list-page.component.css']
})
export class ShoppingListPageComponent implements OnInit {

    public shoppingList: ShoppingList;
    public shoppingListGroupForm: FormGroup;

    constructor(private shoppingListService: ShoppingListService,
                private route: ActivatedRoute,
                private formBuilder: FormBuilder) { }

    public ngOnInit() {
        this.shoppingListGroupForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
        this.shoppingList = this.route.snapshot.data.shoppingList;
    }

    public addShoppingListGroup(): void {
        const name: string = this.shoppingListGroupForm.get('name').value;
        this.shoppingListService.addShoppingListGroup(this.shoppingList, name)
            .subscribe(this.handleShoppingList.bind(this));
    }

    public handleShoppingList(shoppingList: ShoppingList): void {
        this.shoppingList = shoppingList;
    }

}
