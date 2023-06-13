import { Component, OnDestroy, OnInit } from '@angular/core';
import { ShoppingList } from "./shopping-list";
import { UntypedFormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { ShoppingListService } from "./shopping-list.service";
import { HouseholdService } from "../household.service";
import { Household } from "../household";
import { Subscription } from "rxjs/index";
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-shopping-list-page',
    templateUrl: './shopping-list-page.component.html',
    styleUrls: ['./shopping-list-page.component.sass']
})
export class ShoppingListPageComponent implements OnInit, OnDestroy {

    public shoppingList: ShoppingList;
    public shoppingListGroupForm: UntypedFormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading: boolean = false;

    constructor(private shoppingListService: ShoppingListService,
                private activatedRoute: ActivatedRoute,
                private formBuilder: UntypedFormBuilder) { }

    public ngOnInit(): void {
        this.activatedRoute.data.subscribe(({shoppingList}) => {
            this.shoppingList = shoppingList;
        });
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
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
        this.shoppingListGroupForm.controls['name'].setValue('');
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
