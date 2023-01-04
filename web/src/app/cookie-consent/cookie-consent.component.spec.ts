import { ComponentFixture, TestBed, waitForAsync } from '@angular/core/testing';
import { CookieConsentComponent } from './cookie-consent.component';

describe('CookieConsentComponent', () => {
    let component: CookieConsentComponent;
    let fixture: ComponentFixture<CookieConsentComponent>;

    beforeEach(waitForAsync(() => {
        TestBed.configureTestingModule({
            declarations: [ CookieConsentComponent ]
        })
        .compileComponents();
    }));

    beforeEach(() => {
        fixture = TestBed.createComponent(CookieConsentComponent);
        component = fixture.componentInstance;
        fixture.detectChanges();
    });

    afterEach(() => {
        document.cookie = 'cookieConsent=;expires=Thu 01 Jan 1970';
    });

    it('should create', () => {
        expect(component).toBeTruthy();
    });

    it('should render layer if no consent was given', () => {
        expect(component.isLayerVisible()).toBeTruthy();
    });

    it('should give consent', () => {
        expect(component.isLayerVisible()).toBeTruthy();

        component.giveConsent();

        expect(component.isLayerVisible()).toBeFalsy();
        expect(document.cookie).toContain('cookieConsent');
    });

    it('should not render layer if consent was already given', () => {
        const d = new Date();
        d.setTime(d.getTime() + (356 * 24 * 60 * 60 * 1000));
        const expires = `expires=${d.toUTCString()}`;
        document.cookie = document.cookie =  `cookieConsent=1;${expires};path=/`;

        expect(component.isLayerVisible()).toBeFalsy();
    });

});
