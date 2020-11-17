import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { UndoHintComponent } from './undo-hint.component';
import { BrowserAnimationsModule } from "@angular/platform-browser/animations";

describe('UndoHintComponent', () => {
    let component: UndoHintComponent;
    let fixture: ComponentFixture<UndoHintComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [
                BrowserAnimationsModule
            ],
            declarations: [
                UndoHintComponent
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(UndoHintComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
