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

    public expanded: boolean = false;
    public readonly: boolean = true;

    constructor(private cleaningPlanService: CleaningPlanService) { }

    public ngOnInit(): void {
    }

    public selectChore(): void {
        this.cleaningPlanService.selectChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => this.cleaningPlanEmitter.emit(cleaningPlan));
    }

    public editChore(): void {
        this.readonly = false;
    }

    public saveChore(): void {
        this.readonly = true;
    }

    public deleteChore(): void {
        this.cleaningPlanService.deleteChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => this.cleaningPlanEmitter.emit(cleaningPlan));
    }

    public toggleChore(): void {
        this.expanded = !this.expanded;
        this.readonly = true;
    }

    public isGreen(): boolean {
        return this.determineFactor() >= 0.25;
    }

    public isYellow(): boolean {
        const factor: number = this.determineFactor();
        return factor < 0.25 && factor >= 0.1;
    }

    public isRed(): boolean {
        return this.determineFactor() < 0.1;
    }

    private determineFactor(): number {
        const difference1: number = this.chore.nextTime - this.chore.lastPerformed;
        const difference2: number = this.chore.nextTime - Date.now();

        if (difference2 < 0) {
            return 0;
        } else {
            return difference2 / difference1;
        }
    }
}
