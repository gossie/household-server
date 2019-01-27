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

    private loading: boolean = false;

    constructor(private shoppingListService: ShoppingListService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.shoppingListItemForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
    }

    public isNotGlobal(): boolean {
        return this.shoppingListGroup.name !== 'Global';
    }

    public addShoppingListItem(): void {
        this.loading = true;
        const name: string = this.shoppingListItemForm.get('name').value;
        this.shoppingListService.addShoppingListItem(this.shoppingListGroup, name)
            .subscribe(this.handleShoppingList.bind(this));
    }

    public deleteShoppingListGroup(): void {
        this.loading = true;
        this.shoppingListService.deleteShoppingListGroup(this.shoppingListGroup)
            .subscribe(this.handleShoppingList.bind(this));
    }

    public clearShoppingListGroup(): void {
        this.loading = true;
        this.shoppingListService.clearShoppingListGroup(this.shoppingListGroup)
            .subscribe(this.handleShoppingList.bind(this));
    }

    public handleShoppingList(shoppingList: ShoppingList): void {
        this.shoppingListEmitter.emit(shoppingList);
        this.loading = false;
    }

    public isLoading(): boolean {
        return this.loading;
    }
}
