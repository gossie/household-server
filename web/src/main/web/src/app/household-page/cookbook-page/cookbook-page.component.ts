import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from "@angular/router";
import { Cookbook } from "./cookbook";

@Component({
  selector: 'app-cookbook-page',
  templateUrl: './cookbook-page.component.html',
  styleUrls: ['./cookbook-page.component.css']
})
export class CookbookPageComponent implements OnInit {

    public cookbook: Cookbook;

    constructor(private route: ActivatedRoute) { }

    public ngOnInit() {
        this.cookbook = this.route.snapshot.data.cookbook;
    }

}
