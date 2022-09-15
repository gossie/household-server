import { Component, OnInit, Input, Output, EventEmitter, OnDestroy } from '@angular/core';
import { Chore } from './chore';
import { CleaningPlanService } from "../cleaning-plan.service";
import { CleaningPlan } from "../cleaning-plan";
import { UntypedFormBuilder, UntypedFormGroup, Validators } from "@angular/forms";
import { Subscription } from "rxjs/index";
import { DeleteHintService } from "../../delete-hint.service";

@Component({
    selector: 'app-chore',
    templateUrl: './chore.component.html',
    styleUrls: ['./chore.component.sass']
})
export class ChoreComponent implements OnInit, OnDestroy {

    private static readonly TWELVE_HOURS: number = 43200000;

    @Input()
    public chore: Chore;
    
    @Output()
    public cleaningPlanEmitter: EventEmitter<CleaningPlan> = new EventEmitter();

    public choreForm: UntypedFormGroup;
    public expanded: boolean = false;
    public readonly: boolean = true;
    public loading: boolean = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private cleaningPlanService: CleaningPlanService,
                private deleteHintService: DeleteHintService,
                private formBuilder: UntypedFormBuilder) { }

    public ngOnInit(): void {
        this.observeUndo();
        this.createForm();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private observeUndo(): void {
        this.subscriptions.push(this.deleteHintService.onUndo()
            .subscribe(() => {
                this.chore.hidden = false;
                this.loading = false;
            }));
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
        this.chore.hidden = true;
        this.cleaningPlanService.deleteChore(this.chore)
            .subscribe((cleaningPlan: CleaningPlan) => this.cleaningPlanEmitter.emit(cleaningPlan));
    }

    public toggleChore(): void {
        this.expanded = !this.expanded;
        this.readonly = true;
    }

    public isGreen(): boolean {
        return this.chore.nextTime > (Date.now() + ChoreComponent.TWELVE_HOURS);
    }

    public isYellow(): boolean {
        return this.chore.nextTime > Date.now()
            && this.chore.nextTime <= (Date.now() + ChoreComponent.TWELVE_HOURS);
    }

    public isRed(): boolean {
        return this.chore.nextTime <= Date.now();
    }
}
