import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';

import { ChangePasswordComponent } from './change-password.component';
import { ReactiveFormsModule } from "@angular/forms";
import { UserService } from "../../../user.service";
import { UserServiceMock } from "../../../user.service.mock";

describe('ChangePasswordComponent', () => {
    let component: ChangePasswordComponent;
    let fixture: ComponentFixture<ChangePasswordComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            imports: [ReactiveFormsModule],
            declarations: [ ChangePasswordComponent ],
            providers: [
                { provide: UserService, useClass: UserServiceMock }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(ChangePasswordComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
