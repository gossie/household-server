import {Component, EventEmitter, Input, Output} from '@angular/core';
import {ShoppingListItem} from './shopping-list-item';
import {ShoppingListService} from '../../shopping-list.service';
import {ShoppingList} from '../../shopping-list';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';

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
    public editForm: FormGroup;

    constructor(private shoppingListService: ShoppingListService,
                private formBuilder: FormBuilder) { }

    public toggleShoppingListItem(event: any): void {
        if (!this.editMode) {
            this.shoppingListService.toggleShoppingListItem(this.shoppingListItem)
                .subscribe((shoppingList: ShoppingList) => this.shoppingListEmitter.emit(shoppingList));
        }
    }

    public enableEditMode(): void {
        this.editForm = this.formBuilder.group({
            name: [this.shoppingListItem.name, Validators.required]
        });
        this.editMode = true;
    }

    public saveShoppingListItem(): void {
        this.shoppingListItem.name = this.editForm.controls.name.value;
        this.shoppingListService.editShoppingListItem(this.shoppingListItem)
            .subscribe((shoppingList: ShoppingList) => {
                this.editMode = false;
                this.shoppingListEmitter.emit(shoppingList);
            });
    }

}
