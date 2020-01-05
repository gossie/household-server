import { Component, OnDestroy, OnInit } from '@angular/core';
import { ShoppingList } from "./shopping-list";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";
import { ShoppingListService } from "./shopping-list.service";
import { HouseholdService } from "../household.service";
import { Household } from "../household";
import { Subscription } from "rxjs/index";

@Component({
    selector: 'app-shopping-list-page',
    templateUrl: './shopping-list-page.component.html',
    styleUrls: ['./shopping-list-page.component.sass']
})
export class ShoppingListPageComponent implements OnInit, OnDestroy {

    public shoppingList: ShoppingList;
    public shoppingListGroupForm: FormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;

    constructor(private householdService: HouseholdService,
                private shoppingListService: ShoppingListService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.observeHousehold();
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeHousehold(): void {
        this.subscriptions.push(this.householdService.observeHousehold()
            .subscribe((household: Household) => {
                this.shoppingListService.determineShoppingList(household)
                    .subscribe((shoppingList: ShoppingList) => this.shoppingList = shoppingList);
            }));
    }

    private createForm(): void {
        this.shoppingListGroupForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
    }

    public addShoppingListGroup(): void {
        this.loading = true;
        const name: string = this.shoppingListGroupForm.get('name').value;
        this.shoppingListService.addShoppingListGroup(this.shoppingList, name)
            .subscribe((shoppingList: ShoppingList) => {
                localStorage.setItem(`${name}_expanded`, 'true');
                this.handleShoppingList(shoppingList);
            });
    }

    public handleShoppingList(shoppingList: ShoppingList): void {
        this.shoppingList = shoppingList;
        this.loading = false;
        this.shoppingListGroupForm.controls.name.setValue('');
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
