import { Component, OnDestroy, OnInit } from '@angular/core';
import { LoadingService } from "../loading.service";
import { Subscription } from "rxjs/index";

@Component({
    selector: 'app-loading',
    templateUrl: './loading.component.html',
    styleUrls: ['./loading.component.css']
})
export class LoadingComponent implements OnInit, OnDestroy {

    public visible: boolean = false;

    private subscriptions: Array<Subscription> = [];

    constructor(private loadingService: LoadingService) { }

    public ngOnInit(): void {
        this.subscriptions.push(this.loadingService.observeRequest()
            .subscribe((status: boolean) => this.visible = status));
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }

}
