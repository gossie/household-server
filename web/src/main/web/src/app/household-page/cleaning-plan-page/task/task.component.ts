import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
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

    constructor(private cleaningPlanService: CleaningPlanService) { }

    public select() {
        this.task.done = !this.task.done;
        this.cleaningPlanService.selectTask(this.task)
            .subscribe((cleaningPlan: CleaningPlan) => {
                this.cleaningPlanEmitter.emit(cleaningPlan);
            });
    }
}
