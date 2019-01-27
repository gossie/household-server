import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { CleaningPlan } from "./cleaning-plan";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {CleaningPlanService} from "./cleaning-plan.service";
import {Chore} from "./chore/chore";

@Component({
  selector: 'app-cleaning-plan-page',
  templateUrl: './cleaning-plan-page.component.html',
  styleUrls: ['./cleaning-plan-page.component.css']
})
export class CleaningPlanPageComponent implements OnInit {

    public cleaningPlan: CleaningPlan;
    public cleaningPlanForm: FormGroup;

    private loading: boolean = false;

    constructor(private cleaningPlanService: CleaningPlanService,
                private formBuilder: FormBuilder,
                private route: ActivatedRoute) { }

    public ngOnInit() {
        this.cleaningPlan = this.route.snapshot.data.cleaningPlan;
        this.cleaningPlanForm = this.formBuilder.group({
            name: ['', Validators.required],
            repeat: ['', [Validators.required, Validators.min(1), Validators.max(365)]]
        });
        console.log('cleaningPlan', this.cleaningPlan);
    }

    public addChore(): void {
        this.loading = true;

        const chore: Chore = {
            name: this.cleaningPlanForm.controls.name.value,
            repeat: this.cleaningPlanForm.controls.repeat.value
        };

        this.cleaningPlanService.addChore(this.cleaningPlan, chore)
            .subscribe(this.handleCleaningPlan.bind(this));
    }

    public handleCleaningPlan(cleaningPlan: CleaningPlan): void {
        this.cleaningPlan = cleaningPlan;
        this.loading = false;
    }

    public isLoading(): boolean {
        return this.loading;
    }

}
