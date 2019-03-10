import { Injectable } from '@angular/core';
import {HttpEvent, HttpEventType, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import { Observable, Subject } from "rxjs/index";
import {catchError, tap} from "rxjs/internal/operators";
import { LoadingService } from "./loading.service";

@Injectable()
export class LoadingInterceptor implements HttpInterceptor {

    constructor(private loadingService: LoadingService) { }

    public intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        return next.handle(req)
            .pipe(
                tap((event: HttpEvent<any>) => {
                    if (event.type === HttpEventType.Sent) {
                        this.loadingService.broadcastStatus(true);
                    } else if (event.type === HttpEventType.Response) {
                        this.loadingService.broadcastStatus(false);
                    }
                })
            );
    }

}
