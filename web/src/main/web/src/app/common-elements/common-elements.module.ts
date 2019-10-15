import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CheckboxComponent } from './checkbox/checkbox.component';
import { UndoHintComponent } from './undo-hint/undo-hint.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        CheckboxComponent,
        UndoHintComponent
    ],
    exports: [
        CheckboxComponent,
        UndoHintComponent
    ]
})
export class CommonElementsModule { }
