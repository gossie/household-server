import { Component, Input, OnInit } from '@angular/core';

@Component({
    selector: 'app-progress-bar',
    templateUrl: './progress-bar.component.html',
    styleUrls: ['./progress-bar.component.sass']
})
export class ProgressBarComponent implements OnInit {

    @Input()
    public numberOfSelectedItems: number;
    @Input()
    public numberOfItems: number;

    public percentage: number;
    private ratio: number;

    constructor() { }

    public ngOnInit(): void {
        if (this.numberOfItems > 0) {
            this.ratio = this.numberOfSelectedItems / this.numberOfItems;
            this.percentage = Math.round(this.ratio * 100)
        } else {
            this.ratio = 0;
            this.percentage = 0;
        }
    }

    public isWarning(): boolean {
        return this.ratio < 0.25;
    }

    public isInfo(): boolean {
        return this.ratio >= 0.25 && this.ratio < 0.75;
    }

    public isSuccess(): boolean {
        return this.ratio >= 0.75;
    }

}
