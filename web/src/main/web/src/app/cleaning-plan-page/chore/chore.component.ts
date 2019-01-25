import { Component, OnInit, Input } from '@angular/core';
import { Chore } from './chore';

@Component({
    selector: 'app-chore',
    templateUrl: './chore.component.html',
    styleUrls: ['./chore.component.css']
})
export class ChoreComponent implements OnInit {

    @Input()
    public chore: Chore;

    constructor() { }

    ngOnInit() {
    }

    public selectChore(): void {
        
    }

}
