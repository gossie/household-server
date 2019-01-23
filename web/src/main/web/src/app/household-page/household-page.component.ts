import { Component, OnInit } from '@angular/core';
import { Household } from './household';
import { Link } from "../model";
import { ActivatedRoute, Router, NavigationEnd } from "@angular/router";
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-household-page',
  templateUrl: './household-page.component.html',
  styleUrls: ['./household-page.component.css']
})
export class HouseholdPageComponent implements OnInit {

    public household: Household;
    public expanded: boolean = false;

    constructor(private router: Router,
                private route: ActivatedRoute) { }

    public ngOnInit(): void {
        this.household = this.route.snapshot.data.household;
        this.router.events
            .pipe(
                filter(evt => evt instanceof NavigationEnd)
            )
            .subscribe((evt: NavigationEnd) => {
                this.expanded = false;
            });
    }

    public determineUrl(rel: string): string {
        return this.household.links.find((link: Link) => link.rel === rel).href;
    }

    public toggleNavbar(): void {
        this.expanded = !this.expanded;
    }
}
