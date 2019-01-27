import { async, ComponentFixture, TestBed } from '@angular/core/testing';
import { CoverPageComponent } from './cover-page.component';
import { ReactiveFormsModule } from "@angular/forms";
import { InvitationService } from "./invitation.service";
import { InvitationServiceMock } from "./invitation.service.mock";
import {ActivatedRoute} from "@angular/router";
import {Household} from "../household";

describe('CoverPageComponent', () => {
    let component: CoverPageComponent;
    let fixture: ComponentFixture<CoverPageComponent>;
    const household: Household = {
        participants: [],
        links: []
    }

    beforeEach(async(() => {
        TestBed.configureTestingModule({
            imports: [
                ReactiveFormsModule
            ],
            declarations: [ CoverPageComponent ],
            providers: [
                { provide: InvitationService, useClass: InvitationServiceMock },
                { provide: ActivatedRoute, useValue: { snapshot: { data: { household: household } } } }
            ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CoverPageComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });
});
