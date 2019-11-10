import { Injectable } from '@angular/core';
import { HttpEvent, HttpHandler, HttpInterceptor, HttpRequest } from '@angular/common/http';
import { Observable, Subscription, timer } from 'rxjs/index';
import { filter, mergeMap, tap } from 'rxjs/internal/operators';
import { DeleteHintService } from './delete-hint.service';
import { LoadingService } from './loading.service';

@Injectable()
export class DeleteInterceptor implements HttpInterceptor {

    constructor(private deleteHintService: DeleteHintService,
                private loadingService: LoadingService) { }

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        if (req.method === 'DELETE') {
            let canceled = false;
            const subscription: Subscription = this.deleteHintService.onUndo()
                .subscribe(() => {
                    canceled = true;
                    this.loadingService.broadcastStatus(false)
                    subscription.unsubscribe();
                });

            this.deleteHintService.show();

            return timer(3000)
                .pipe(
                    filter(() => !canceled),
                    tap(() => this.deleteHintService.hide()),
                    tap(() => subscription.unsubscribe()),
                    mergeMap(() => next.handle(req))
                );
        } else {
            return next.handle(req);
        }
    }
}
