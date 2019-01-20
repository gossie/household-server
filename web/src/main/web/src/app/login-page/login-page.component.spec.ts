import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { LoginPageComponent } from './login-page.component';
import { ReactiveFormsModule } from '@angular/forms';
import { LoginService } from './login.service';
import { LoginServiceMock } from './login.service.mock';
import { RouterTestingModule } from '@angular/router/testing';

describe('LoginPageComponent', () => {
    let component: LoginPageComponent;
    let fixture: ComponentFixture<LoginPageComponent>;

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule,
                RouterTestingModule
            ],
            declarations: [ LoginPageComponent ],
            providers: [
                { provide: LoginService, useClass: LoginServiceMock }
            ]
        })
        .compileComponents();
    }));

  beforeEach(() => {
      fixture = TestBed.createComponent(LoginPageComponent);
      component = fixture.componentInstance;
      fixture.detectChanges();
  });

  it('should create', () => {
      expect(component).toBeTruthy();
  });
});
