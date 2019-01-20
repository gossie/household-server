import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {ShoppingListGroup} from "./shopping-list-group";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ShoppingList} from "../shopping-list";
import {ShoppingListService} from "../shopping-list.service";

@Component({
  selector: 'app-shopping-list-group',
  templateUrl: './shopping-list-group.component.html',
  styleUrls: ['./shopping-list-group.component.css']
})
export class ShoppingListGroupComponent implements OnInit {

    @Input()
    public shoppingListGroup: ShoppingListGroup;
    @Output()
    public shoppingListEmitter: EventEmitter<ShoppingList> = new EventEmitter();

    public shoppingListItemForm: FormGroup;

    constructor(private shoppingListService: ShoppingListService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.shoppingListItemForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
    }

    public addShoppingListItem(): void {
        const name: string = this.shoppingListItemForm.get('name').value;
        this.shoppingListService.addShoppingListItem(this.shoppingListGroup, name)
            .subscribe((shoppingList: ShoppingList) => {
                this.shoppingListEmitter.emit(shoppingList);
            });
    }

}
