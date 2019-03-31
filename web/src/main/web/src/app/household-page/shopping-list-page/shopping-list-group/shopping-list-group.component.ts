import { Component, EventEmitter, Input, OnChanges, OnDestroy, OnInit, Output } from '@angular/core';
import { ShoppingListGroup } from "./shopping-list-group";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ShoppingList } from "../shopping-list";
import { ShoppingListService } from "../shopping-list.service";
import { ShoppingListItem } from "./shopping-list-item/shopping-list-item";
import { DeleteHintService } from "../../delete-hint.service";
import {from, Subscription} from "rxjs/index";
import { StringUtils } from "../../../string.utils";

@Component({
    selector: 'app-shopping-list-group',
    templateUrl: './shopping-list-group.component.html',
    styleUrls: ['./shopping-list-group.component.sass']
})
export class ShoppingListGroupComponent implements OnInit, OnChanges, OnDestroy {

    @Input()
    public shoppingListGroup: ShoppingListGroup;
    @Output()
    public shoppingListEmitter: EventEmitter<ShoppingList> = new EventEmitter();

    public shoppingListItemForm: FormGroup;
    public clearButtonActive: boolean;
    public checked: boolean;
    public loading: boolean = false;
    public expanded: boolean = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private shoppingListService: ShoppingListService,
                private deleteHintService: DeleteHintService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.expanded = this.isInitiallyExpanded();
        this.subscriptions.push(this.deleteHintService.onUndo()
            .subscribe(() => {
                this.shoppingListGroup.hidden = false;
                this.shoppingListGroup.shoppingListItems
                    .forEach((shoppingListItem: ShoppingListItem) => shoppingListItem.hidden = false);
            }));

        this.shoppingListItemForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
    }

    public ngOnChanges(): void {
        this.shoppingListGroup.shoppingListItems.sort(this.compareItems.bind(this));
        this.clearButtonActive = this.shoppingListGroup.shoppingListItems.some((item: ShoppingListItem) => item.selected === true);
        this.checked = this.shoppingListGroup.shoppingListItems.length > 0
            && this.shoppingListGroup.shoppingListItems.every((item: ShoppingListItem) => item.selected);
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private isInitiallyExpanded(): boolean {
        const fromLocalStorage = localStorage.getItem(`${this.shoppingListGroup.name}_expanded`);
        if (StringUtils.isEmpty(fromLocalStorage)) {
            return this.shoppingListGroup.name === 'Global';
        } else {
            return fromLocalStorage === 'true'
        }
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
        this.shoppingListGroup.hidden = true;
        this.shoppingListService.deleteShoppingListGroup(this.shoppingListGroup)
            .subscribe(this.handleShoppingList.bind(this));
    }

    public clearShoppingListGroup(): void {
        this.loading = true;
        this.hideSelectedItems();
        this.shoppingListService.clearShoppingListGroup(this.shoppingListGroup)
            .subscribe(this.handleShoppingList.bind(this));
    }

    private hideSelectedItems(): void {
        this.shoppingListGroup.shoppingListItems
            .filter((shoppingListItem: ShoppingListItem) => shoppingListItem.selected)
            .forEach((shoppingListItem: ShoppingListItem) => shoppingListItem.hidden = true);
    }

    public toggleAllShoppingListItems(): void {
        this.shoppingListService.toggleShoppingListGroup(this.shoppingListGroup)
            .subscribe(this.handleShoppingList.bind(this));
    }

    public handleShoppingList(shoppingList: ShoppingList): void {
        this.shoppingListEmitter.emit(shoppingList);
        this.loading = false;
    }

    public toggleGroup(): void {
        this.expanded = !this.expanded;
        localStorage.setItem(`${this.shoppingListGroup.name}_expanded`, `${this.expanded}`);
    }
}
