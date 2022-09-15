import { Component, EventEmitter, Input, Output } from '@angular/core';
import { ShoppingListItem } from './shopping-list-item';
import { ShoppingListService } from '../../shopping-list.service';
import { ShoppingList } from '../../shopping-list';
import { UntypedFormGroup, UntypedFormBuilder, Validators } from '@angular/forms';
import { Link } from 'src/app/model';
import '@gossie/array-pipe';
import { filter, map } from '@gossie/array-pipe/operators';

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
    public editForm: UntypedFormGroup;
    public showImage = false;

    constructor(private shoppingListService: ShoppingListService,
                private formBuilder: UntypedFormBuilder) { }

    public toggleShoppingListItem(): void {
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

    public hasImageLink(): boolean {
        return this.shoppingListItem.links.some((link: Link) => link.rel === 'image');
    }

    public handleFileInput(files: FileList): void {
        const file: File = files.item(0);
        const reader = new FileReader();
        reader.onloadend = () => {
            this.shoppingListItem.image = (<string> reader.result).split(',')[1];
            this.shoppingListService.editShoppingListItem(this.shoppingListItem)
                .subscribe((shoppingList: ShoppingList) => {
                    this.editMode = false;
                    this.shoppingListEmitter.emit(shoppingList);
                });
        };
        reader.readAsDataURL(file);
    }

    public saveShoppingListItem(): void {
        this.shoppingListItem.name = this.editForm.controls.name.value;
        this.shoppingListService.editShoppingListItem(this.shoppingListItem)
            .subscribe((shoppingList: ShoppingList) => {
                this.editMode = false;
                this.shoppingListEmitter.emit(shoppingList);
            });
    }

    public openImageDialog(): void {
        this.showImage = true;
    }

    public getImageUrl(): string {
        return this.shoppingListItem.links
            .pipe(
                filter((link: Link) => link.rel === 'image'),
                map((link: Link) => link.href)
            )[0];
    }

    public closeImageDialog(): void {
        this.showImage = false;
    }

}
