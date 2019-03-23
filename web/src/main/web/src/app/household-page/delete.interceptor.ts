import { Injectable } from '@angular/core';
import { HttpEvent, HttpEventType, HttpHandler, HttpInterceptor, HttpRequest } from "@angular/common/http";
import { Observable, Subscription, timer } from "rxjs/index";
import {delayWhen, filter, mergeMap, tap} from "rxjs/internal/operators";
import { DeleteHintService } from "./delete-hint.service";

@Injectable()
export class DeleteInterceptor implements HttpInterceptor {

    constructor(private deleteHintService: DeleteHintService) { }

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.method === 'DELETE') {
            let canceled: boolean = false;
            const subscription: Subscription = this.deleteHintService.onUndo()
                .subscribe(() => {
                    canceled = true;
                    subscription.unsubscribe();
                });

            this.deleteHintService.show();

            return timer(3000)
                .pipe(
                    filter(() => !canceled),
                    tap(() => this.deleteHintService.hide()),
                    tap(() => subscription.unsubscribe()),
                    mergeMap(() => {
                        console.log('perform request');
                        return next.handle(req)
                    })
                );
        } else {
            return next.handle(req);
        }
    }
}
