import { Component, OnDestroy, OnInit } from '@angular/core';
import { CleaningPlan } from './cleaning-plan';
import { UntypedFormBuilder, UntypedFormGroup, Validators } from '@angular/forms';
import { CleaningPlanService } from './cleaning-plan.service';
import { Chore } from './chore/chore';
import { Task } from './task/task';
import { Subscription } from 'rxjs/index';
import { ActivatedRoute } from '@angular/router';

@Component({
    selector: 'app-cleaning-plan-page',
    templateUrl: './cleaning-plan-page.component.html',
    styleUrls: ['./cleaning-plan-page.component.sass']
})
export class CleaningPlanPageComponent implements OnInit, OnDestroy {

    public cleaningPlan: CleaningPlan;
    public cleaningPlanForm: UntypedFormGroup;
    public taskForm: UntypedFormGroup;

    private subscriptions: Array<Subscription> = [];
    private loading = false;

    constructor(private cleaningPlanService: CleaningPlanService,
                private formBuilder: UntypedFormBuilder,
                private activatedRoute: ActivatedRoute) { }

    public ngOnInit(): void {
        this.activatedRoute.data.subscribe(({cleaningPlan}) => this.handleCleaningPlan(cleaningPlan));
        this.createForms();
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

    private createForms(): void {
        this.cleaningPlanForm = this.formBuilder.group({
            name: ['', Validators.required],
            repeat: ['', [Validators.required, Validators.min(0), Validators.max(365)]]
        });

        this.taskForm = this.formBuilder.group({
            name: ['', Validators.required]
        });
    }

    public addChore(): void {
        this.loading = true;

        const chore: Chore = {
            name: this.cleaningPlanForm.controls['name'].value,
            repeat: this.cleaningPlanForm.controls['repeat'].value,
            lastPerformed: Date.now()
        };

        this.cleaningPlanService.addChore(this.cleaningPlan, chore)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.handleCleaningPlan(cleaningPlan);
                this.cleaningPlanForm.controls['name'].setValue('');
                this.cleaningPlanForm.controls['repeat'].setValue('');
            });
    }

    public addTask(): void {
        this.loading = true;

        const task: Task = {
            name: this.taskForm.controls['name'].value,
            done: false
        };

        this.cleaningPlanService.addTask(this.cleaningPlan, task)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.handleCleaningPlan(cleaningPlan);
                this.taskForm.controls['name'].setValue('');
            });
    }

    public handleCleaningPlan(cleaningPlan: CleaningPlan): void {
        cleaningPlan.chores.sort((chore1: Chore, chore2: Chore) => chore1.nextTime - chore2.nextTime);
        cleaningPlan.tasks.sort((task1, task2) => {
            if (task1.done) {
                return 1;
            } else if (task2.done) {
                return -1;
            } else {
                return 0;
            }
        });
        this.cleaningPlan = cleaningPlan;
        this.loading = false;
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
