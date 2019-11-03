import { TestBed } from '@angular/core/testing';

import { CustomXsrfInterceptor as CustomXsrfInterceptor } from './custom-xsrf.interceptor';

describe('CustomXsrfInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({}));

  it('should be created', () => {
    const service: CustomXsrfInterceptor = TestBed.get(CustomXsrfInterceptor);
    expect(service).toBeTruthy();
  });
});
