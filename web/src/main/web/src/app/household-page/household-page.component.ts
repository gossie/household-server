import {Component, OnInit} from '@angular/core';
import { Household } from './household';
import { Link } from "../model";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-household-page',
  templateUrl: './household-page.component.html',
  styleUrls: ['./household-page.component.css']
})
export class HouseholdPageComponent implements OnInit {

    public household: Household;

    constructor(private route: ActivatedRoute) { }

    public ngOnInit(): void {
        this.household = this.route.snapshot.data.household;
    }

    public determineUrl(rel: string): string {
        return this.household.links.find((link: Link) => link.rel === rel).href;
    }
}
