import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CheckboxComponent } from './checkbox/checkbox.component';
import { UndoHintComponent } from './undo-hint/undo-hint.component';
import { InvitationComponent } from './invitation/invitation.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        CheckboxComponent,
        UndoHintComponent,
        InvitationComponent
    ],
    exports: [
        CheckboxComponent,
        UndoHintComponent,
        InvitationComponent
    ]
})
export class CommonElementsModule { }
