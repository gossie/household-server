import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';
import { Chore } from './chore';
import { CleaningPlanService } from "../cleaning-plan.service";
import { CleaningPlan } from "../cleaning-plan";
import { FormBuilder, FormGroup, Validators } from "@angular/forms";

@Component({
    selector: 'app-chore',
    templateUrl: './chore.component.html',
    styleUrls: ['./chore.component.sass']
})
export class ChoreComponent implements OnInit {

    @Input()
    public chore: Chore;
    @Output()
    public cleaningPlanEmitter: EventEmitter<CleaningPlan> = new EventEmitter();

    public choreForm: FormGroup;
    public expanded: boolean = false;
    public readonly: boolean = true;
    public loading: boolean = false;

    constructor(private cleaningPlanService: CleaningPlanService,
                private formBuilder: FormBuilder) { }

    public ngOnInit(): void {
        this.createForm();
    }

    private createForm(): void {
        this.choreForm = this.formBuilder.group({
            name: [this.chore.name, Validators.required],
            repeat: [this.chore.repeat, [Validators.required, Validators.min(1), Validators.max(365)]]
        });
    }

    public selectChore(): void {
        this.loading = true;
        this.cleaningPlanService.selectChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.cleaningPlanEmitter.emit(cleaningPlan);
                this.loading = false;
            });
    }

    public editChore(): void {
        this.readonly = false;
    }

    public saveChore(): void {
        this.loading = true;

        this.chore.name = this.choreForm.controls.name.value;
        this.chore.repeat = this.choreForm.controls.repeat.value;

        this.cleaningPlanService.saveChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.cleaningPlanEmitter.emit(cleaningPlan);
                this.readonly = true;
                this.loading = false;
            });
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
