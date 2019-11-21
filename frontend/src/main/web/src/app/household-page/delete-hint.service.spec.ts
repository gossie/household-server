import { TestBed } from '@angular/core/testing';
import { DeleteHintService } from './delete-hint.service';

describe('DeleteHintService', () => {
    beforeEach(() => TestBed.configureTestingModule({}));

    it('should be created', () => {
        const service: DeleteHintService = TestBed.get(DeleteHintService);
        expect(service).toBeTruthy();
    });

    it('should show hint', done => {
        const service: DeleteHintService = TestBed.get(DeleteHintService);
        service.onVisibilityChange()
            .subscribe((value: boolean) => {
                expect(value).toBeTruthy();
                done();
            });

        service.show();
    });

    it('should hide hint', done => {
        const service: DeleteHintService = TestBed.get(DeleteHintService);
        service.onVisibilityChange()
            .subscribe((value: boolean) => {
                expect(value).toBeFalsy();
                done();
            });

        service.hide();
    });

    it('should undo deletion', () => {
        let undoHandlerCalled: boolean = false;
        const service: DeleteHintService = TestBed.get(DeleteHintService);
        service.onVisibilityChange()
            .subscribe((value: boolean) => expect(value).toBeFalsy());
        service.onUndo()
            .subscribe(() => undoHandlerCalled = true);

        service.undoDeletion();

        expect(undoHandlerCalled).toBeTruthy();
    });
});
