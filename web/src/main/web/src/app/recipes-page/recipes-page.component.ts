import { Component, OnInit } from '@angular/core';
import {ActivatedRoute} from "@angular/router";
import {Cookbook} from "./cookbook";

@Component({
  selector: 'app-recipes-page',
  templateUrl: './recipes-page.component.html',
  styleUrls: ['./recipes-page.component.css']
})
export class RecipesPageComponent implements OnInit {

    public cookbook: Cookbook;

    constructor(private route: ActivatedRoute) { }

    public ngOnInit() {
        this.cookbook = this.route.snapshot.data.cookbook;
    }

}
