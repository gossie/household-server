import { Component, EventEmitter, Input, OnChanges, OnInit, Output } from '@angular/core';
import { ShoppingListGroup } from "./shopping-list-group";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ShoppingList } from "../shopping-list";
import { ShoppingListService } from "../shopping-list.service";
import { ShoppingListItem } from "./shopping-list-item/shopping-list-item";

@Component({
    selector: 'app-shopping-list-group',
    templateUrl: './shopping-list-group.component.html',
    styleUrls: ['./shopping-list-group.component.css']
})
export class ShoppingListGroupComponent implements OnInit, OnChanges {

    @Input()
    public shoppingListGroup: ShoppingListGroup;
    @Output()
    public shoppingListEmitter: EventEmitter<ShoppingList> = new EventEmitter();

    public shoppingListItemForm: FormGroup;
    public clearButtonActive: boolean;
    public loading: boolean = false;

    constructor(private shoppingListService: ShoppingListService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.shoppingListItemForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
    }

    public ngOnChanges(): void {
        this.shoppingListGroup.shoppingListItems.sort(this.compareItems.bind(this));
        this.clearButtonActive = this.shoppingListGroup.shoppingListItems.some((item: ShoppingListItem) => item.selected === true);
    }

    private compareItems(item1: ShoppingListItem, item2: ShoppingListItem): number {
        if(item1.selected === item2.selected) {
            if(item1.name.toLowerCase() < item2.name.toLowerCase()) {
                return -1;
            } else {
                return 1;
            }
        } else {
            if(item1.selected) {
                return 1;
            } else {
                return -1;
            }
        }
    }

    public isNotGlobal(): boolean {
        return this.shoppingListGroup.name !== 'Global';
    }

    public addShoppingListItem(): void {
        this.loading = true;
        const name: string = this.shoppingListItemForm.get('name').value;
        this.shoppingListService.addShoppingListItems(this.shoppingListGroup, [name])
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
}
