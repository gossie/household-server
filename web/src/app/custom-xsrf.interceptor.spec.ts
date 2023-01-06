import { TestBed } from '@angular/core/testing';
import { CustomXsrfInterceptor as CustomXsrfInterceptor } from './custom-xsrf.interceptor';
import { HttpXsrfTokenExtractor } from '@angular/common/http';

describe('CustomXsrfInterceptor', () => {
    beforeEach(() => TestBed.configureTestingModule({
        providers: [
            HttpXsrfTokenExtractor,
            CustomXsrfInterceptor
        ]
    }));

    it('should be created', () => {
        const service: CustomXsrfInterceptor = TestBed.inject(CustomXsrfInterceptor);
        expect(service).toBeTruthy();
    });
});
