import { Component, Input, OnInit } from '@angular/core';
import { Meal } from "./meal";
import { FormControl, FormGroup } from "@angular/forms";

@Component({
    selector: 'app-meal',
    templateUrl: './meal.component.html',
    styleUrls: ['./meal.component.css']
})
export class MealComponent implements OnInit {

    @Input()
    public meal: Meal;
    @Input()
    public day: string;
    @Input()
    public controlName: string
    @Input()
    public parentForm: FormGroup;

    constructor() { }

    public ngOnInit(): void {
        this.parentForm.addControl(this.controlName, new FormControl(this.meal.name));
    }

}
