import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ReactiveFormsModule } from '@angular/forms';
import { By } from '@angular/platform-browser';
import { CommonElementsModule } from 'src/app/common-elements/common-elements.module';
import { CleaningPlanService } from '../cleaning-plan.service';
import { CleaningPlanServiceMock } from '../cleaning-plan.service.mock';

import { TaskComponent } from './task.component';

describe('TaskComponent', () => {
    let component: TaskComponent;
    let fixture: ComponentFixture<TaskComponent>;

    beforeEach(async () => {
        await TestBed.configureTestingModule({
            imports: [ CommonElementsModule, ReactiveFormsModule ],
            declarations: [ TaskComponent ],
            providers: [
                { provide: CleaningPlanService, useClass: CleaningPlanServiceMock }
            ]
        })
        .compileComponents();
    });

    beforeEach(() => {
        fixture = TestBed.createComponent(TaskComponent);
        component = fixture.componentInstance;
        component.task = {
            name: 'task',
            done: false
        };
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should display the name', () => {
        const name = fixture.debugElement.query(By.css('.card-header-title')).nativeElement.innerText;
        expect(name).toBe('task');
    });
    
});
