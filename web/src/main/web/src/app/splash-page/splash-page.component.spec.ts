import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { SplashPageComponent } from './splash-page.component';
import {RouterTestingModule} from "@angular/router/testing";

describe('SplashPageComponent', () => {
    let component: SplashPageComponent;
    let fixture: ComponentFixture<SplashPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                RouterTestingModule
            ],
            declarations: [ SplashPageComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(SplashPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
