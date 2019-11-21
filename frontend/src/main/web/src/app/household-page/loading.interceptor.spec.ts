import { TestBed } from '@angular/core/testing';
import { LoadingInterceptor } from './loading.interceptor';
import {HTTP_INTERCEPTORS} from "@angular/common/http";
import {DeleteInterceptor} from "./delete.interceptor";

describe('LoadingInterceptor', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            { provide: HTTP_INTERCEPTORS, useClass: LoadingInterceptor, multi: true }
        ]
    }));
});
