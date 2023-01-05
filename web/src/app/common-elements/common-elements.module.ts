import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { CheckboxComponent } from './checkbox/checkbox.component';
import { UndoHintComponent } from './undo-hint/undo-hint.component';
import { SelectRecipeComponent } from './select-recipe/select-recipe.component';

@NgModule({
    imports: [
        CommonModule
    ],
    declarations: [
        CheckboxComponent,
        UndoHintComponent,
        SelectRecipeComponent
    ],
    exports: [
        CheckboxComponent,
        UndoHintComponent,
        SelectRecipeComponent
    ]
})
export class CommonElementsModule { }
