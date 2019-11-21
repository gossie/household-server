import { Component, OnDestroy, OnInit } from '@angular/core';
import { DeleteHintService } from '../../household-page/delete-hint.service';
import { Subscription } from 'rxjs/index';
import { animate, state, style, transition, trigger } from '@angular/animations';

@Component({
    selector: 'app-undo-hint',
    templateUrl: './undo-hint.component.html',
    styleUrls: ['./undo-hint.component.sass'],
    animations: [
        trigger('undoHint', [
            state('visible', style({
                opacity: 1,
                zIndex: 1000
            })),
            state('invisible', style({
                opacity: 0,
                zIndex: 0
            })),
            transition('visible => invisible', [
                animate('0.1s')
            ]),
            transition('invisible => visible', [
                animate('1s')
            ])
        ])
    ]
})
export class UndoHintComponent implements OnInit, OnDestroy {

    public visible = false;
    private subscriptions: Array<Subscription> = [];

    constructor(private deleteHintService: DeleteHintService) { }

    public ngOnInit() {
        this.deleteHintService.onVisibilityChange()
            .subscribe((value: boolean) => this.visible = value);
    }

    public ngOnDestroy(): void {
        this.subscriptions.forEach((subscription: Subscription) => subscription.unsubscribe());
    }
    public performUndo(): void {
        this.deleteHintService.undoDeletion();
    }

}
