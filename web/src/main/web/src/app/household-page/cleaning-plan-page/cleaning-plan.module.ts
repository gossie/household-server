import { NgModule } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { CommonModule } from '@angular/common';
import { CleaningPlanRoutingModule } from './cleaning-plan-routing.module';
import { CleaningPlanPageComponent } from './cleaning-plan-page.component';
import { CleaningPlanService } from './cleaning-plan.service';
import { ChoreComponent } from './chore/chore.component';
import { DatePipe } from './chore/date.pipe';

@NgModule({
    imports: [
        CommonModule,
        ReactiveFormsModule,
        HttpClientModule,
        CleaningPlanRoutingModule
    ],
    declarations: [
        CleaningPlanPageComponent,
        ChoreComponent,
        DatePipe
    ],
    providers: [
        CleaningPlanService
    ]
})
export class CleaningPlanModule { }
