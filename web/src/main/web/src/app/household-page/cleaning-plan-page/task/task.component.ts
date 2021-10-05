import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { CleaningPlan } from '../cleaning-plan';
import { CleaningPlanService } from '../cleaning-plan.service';
import { Task } from './task';

@Component({
    selector: 'app-task',
    templateUrl: './task.component.html',
    styleUrls: ['./task.component.sass']
})
export class TaskComponent {

    @Input()
    public task: Task;
    @Output()
    public cleaningPlanEmitter: EventEmitter<CleaningPlan> = new EventEmitter();

    public editForm: FormGroup;
    public editMode: boolean = false;

    constructor(private cleaningPlanService: CleaningPlanService, private formBuilder: FormBuilder) { }

    public select() {
        this.task.done = !this.task.done;
        this.cleaningPlanService.saveTask(this.task)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.cleaningPlanEmitter.emit(cleaningPlan);
            });
    }

    public enableEditMode(): void {
        this.editForm = this.formBuilder.group({
            name: [this.task.name, Validators.required]
        });
        this.editMode = true;
    }

    public saveTask(): void {
        this.task.name = this.editForm.controls.name.value;
        this.cleaningPlanService.saveTask(this.task)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.editMode = false;
                this.cleaningPlanEmitter.emit(cleaningPlan);
            });
    }
}
