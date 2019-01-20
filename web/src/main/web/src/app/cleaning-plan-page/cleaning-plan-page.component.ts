import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {CleaningPlan} from "./cleaning-plan";

@Component({
  selector: 'app-cleaning-plan-page',
  templateUrl: './cleaning-plan-page.component.html',
  styleUrls: ['./cleaning-plan-page.component.css']
})
export class CleaningPlanPageComponent implements OnInit {

    public cleaningPlan: CleaningPlan;

    constructor(private route: ActivatedRoute) { }

    public ngOnInit() {
        this.cleaningPlan = this.route.snapshot.data.cleaningPlan;
        console.log('this.cleaningPlan', this.cleaningPlan);
    }

}
