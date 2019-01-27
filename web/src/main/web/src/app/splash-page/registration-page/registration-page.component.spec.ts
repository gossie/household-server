import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { RegistrationPageComponent } from './registration-page.component';
import {RegistrationService} from "./registration.service";
import {RegistrationServiceMock} from "./registration.service.mock";
import {ReactiveFormsModule} from "@angular/forms";
import {RouterTestingModule} from "@angular/router/testing";

describe('RegistrationPageComponent', () => {
    let component: RegistrationPageComponent;
    let fixture: ComponentFixture<RegistrationPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule,
                RouterTestingModule
            ],
            declarations: [
                RegistrationPageComponent
            ],
            providers: [
                { provide: RegistrationService, useClass: RegistrationServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(RegistrationPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
