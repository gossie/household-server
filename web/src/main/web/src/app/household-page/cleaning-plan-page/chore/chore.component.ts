import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Chore } from './chore';
import { CleaningPlanService } from "../cleaning-plan.service";
import { CleaningPlan } from "../cleaning-plan";

@Component({
    selector: 'app-chore',
    templateUrl: './chore.component.html',
    styleUrls: ['./chore.component.css']
})
export class ChoreComponent implements OnInit {

    @Input()
    public chore: Chore;
    @Output()
    public cleaningPlanEmitter: EventEmitter<CleaningPlan> = new EventEmitter();

    constructor(private cleaningPlanService: CleaningPlanService) { }

    ngOnInit() {
    }

    public selectChore(): void {
        this.cleaningPlanService.selectChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.cleaningPlanEmitter.emit(cleaningPlan);
            });
    }

    public deleteChore(): void {
        this.cleaningPlanService.deleteChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.cleaningPlanEmitter.emit(cleaningPlan);
            });
    }

    public determineDate(timestamp: number): string {
        const date: Date = new Date();
        date.setTime(timestamp);

        return`${data.getDate()}.${data.getMonth() + 1}.${data.getFullYear()}`
    }

}
