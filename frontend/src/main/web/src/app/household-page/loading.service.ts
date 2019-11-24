import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs/index";

@Injectable({
    providedIn: 'root'
})
export class LoadingService {

    private subject: Subject<boolean> = new Subject();

    constructor() { }

    public broadcastStatus(status: boolean): void {
        this.subject.next(status);
    }

    public observeRequest(): Observable<boolean> {
        return this.subject.asObservable();
    }
}
