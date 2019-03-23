import { Injectable } from '@angular/core';
import {Observable, Subject} from "rxjs/index";

@Injectable({
    providedIn: 'root'
})
export class DeleteHintService {

    private undoneSubject: Subject<void> = new Subject();
    private visibilitySubject: Subject<boolean> = new Subject();

    constructor() { }

    public onUndo(): Observable<void> {
        return this.undoneSubject.asObservable();
    }

    public onVisibilityChange(): Observable<boolean> {
        return this.visibilitySubject.asObservable();
    }

    public undoDeletion(): void {
        this.visibilitySubject.next(false);
        this.undoneSubject.next();
    }

    public show(): void {
        console.log('show');
        this.visibilitySubject.next(true);
    }

    public hide(): void {
        console.log('hide');
        this.visibilitySubject.next(false);
    }
}
