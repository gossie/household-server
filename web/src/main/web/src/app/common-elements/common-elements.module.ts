import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CheckboxComponent } from './checkbox/checkbox.component';
import { UndoHintComponent } from './undo-hint/undo-hint.component';
import { InvitationComponent } from './invitation/invitation.component';
import { InvitationService } from './invitation/invitation.service';
import { SelectRecipeComponent } from './select-recipe/select-recipe.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        CheckboxComponent,
        UndoHintComponent,
        InvitationComponent,
        SelectRecipeComponent
    ],
    exports: [
        CheckboxComponent,
        UndoHintComponent,
        InvitationComponent,
        SelectRecipeComponent
    ]
})
export class CommonElementsModule { }
