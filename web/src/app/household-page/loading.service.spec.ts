import { TestBed } from '@angular/core/testing';
import { LoadingService } from './loading.service';

describe('LoadingService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: LoadingService = TestBed.inject(LoadingService);
        expect(service).toBeTruthy();
    });

    it('should broadcast status true', done => {
        const service: LoadingService = TestBed.inject(LoadingService);

        service.observeRequest().subscribe((status: boolean) => {
            expect(status).toBeTruthy();
            done();
        });

        service.broadcastStatus(true);
    });

    it('should broadcast status false', done => {
        const service: LoadingService = TestBed.inject(LoadingService);

        service.observeRequest().subscribe((status: boolean) => {
            expect(status).toBeFalsy();
            done();
        });

        service.broadcastStatus(false);
    });
});
