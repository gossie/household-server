import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CleaningPlanPageComponent } from './cleaning-plan-page.component';

describe('CleaningPlanPageComponent', () => {
  let component: CleaningPlanPageComponent;
  let fixture: ComponentFixture<CleaningPlanPageComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CleaningPlanPageComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CleaningPlanPageComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
