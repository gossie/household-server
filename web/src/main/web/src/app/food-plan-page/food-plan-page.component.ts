import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {FoodPlan} from "./food-plan";

@Component({
  selector: 'app-food-plan-page',
  templateUrl: './food-plan-page.component.html',
  styleUrls: ['./food-plan-page.component.css']
})
export class FoodPlanPageComponent implements OnInit {

    public foodPlan: FoodPlan;

    constructor(private route: ActivatedRoute) { }

    public ngOnInit() {
        this.foodPlan = this.route.snapshot.data.foodPlan;
        console.log('this.foodPlan', this.foodPlan);
    }

}
