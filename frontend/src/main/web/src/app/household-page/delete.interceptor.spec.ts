import { TestBed } from '@angular/core/testing';

import { DeleteInterceptor } from './delete.interceptor';
import {HTTP_INTERCEPTORS} from "@angular/common/http";

describe('DeleteInterceptor', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            { provide: HTTP_INTERCEPTORS, useClass: DeleteInterceptor, multi: true }
        ]
    }));

    it('should be created', () => {
        const service: DeleteInterceptor = TestBed.get(HTTP_INTERCEPTORS);
        expect(service).toBeTruthy();
    });
});
