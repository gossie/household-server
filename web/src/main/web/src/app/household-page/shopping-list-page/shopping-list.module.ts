import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule, HttpClientXsrfModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CommonElementsModule } from '../../common-elements/common-elements.module';
import { ShoppingListRoutingModule } from './shopping-list-routing.module';
import { ShoppingListPageComponent } from './shopping-list-page.component';
import { ShoppingListGroupComponent } from './shopping-list-group/shopping-list-group.component';
import { ShoppingListItemComponent } from './shopping-list-group/shopping-list-item/shopping-list-item.component';
import { ProgressBarComponent } from './shopping-list-group/progress-bar/progress-bar.component';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        HttpClientXsrfModule.withOptions({
            cookieName: 'XSRF-TOKEN',
            headerName: 'XSRF-TOKEN'
        }),
        CommonElementsModule,
        ShoppingListRoutingModule
    ],
    declarations: [
        ShoppingListPageComponent,
        ShoppingListGroupComponent,
        ShoppingListItemComponent,
        ProgressBarComponent
    ]
})
export class ShoppingListModule { }
