import {Component, EventEmitter, Input, OnInit, Output} from '@angular/core';
import {CheckboxValue} from './checkbox-value';

@Component({
    selector: 'app-checkbox',
    templateUrl: './checkbox.component.html',
    styleUrls: ['./checkbox.component.sass']
})
export class CheckboxComponent {

    @Input()
    public label: string;
    @Input()
    public selected = false;
    @Output()
    public onSelect: EventEmitter<CheckboxValue> = new EventEmitter();

    constructor() { }

    public toggleCheckbox(): void {
        this.selected = !this.selected;
        this.onSelect.emit({
            selected: this.selected,
            value: this.label
        });
    }

}
