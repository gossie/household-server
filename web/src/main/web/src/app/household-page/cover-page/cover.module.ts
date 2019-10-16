import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CommonElementsModule } from '../../common-elements/common-elements.module';
import { CoverRoutingModule } from './cover-routing.module';
import { CoverPageComponent } from './cover-page.component';
import { ChangePasswordComponent } from './change-password/change-password.component';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        CommonElementsModule,
        CoverRoutingModule
    ],
    declarations: [
        CoverPageComponent,
        ChangePasswordComponent
    ]
})
export class CoverModule { }
