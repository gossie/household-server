import { Component, OnInit, Inject } from '@angular/core';
import { DOCUMENT } from '@angular/common';

@Component({
    selector: 'app-cookie-consent',
    templateUrl: './cookie-consent.component.html',
    styleUrls: ['./cookie-consent.component.sass']
})
export class CookieConsentComponent {

    private static readonly COOKIE_NAME = 'cookieConsent';

    constructor(@Inject(DOCUMENT) private document: Document) { }

    public isLayerVisible(): boolean {
        return this.readCookie().trim().length === 0;
    }

    private readCookie(): string {
        const name = `${CookieConsentComponent.COOKIE_NAME}=`;
        const decodedCookie = decodeURIComponent(document.cookie);
        const ca = decodedCookie.split(';');
        for (let i = 0; i < ca.length; i++) {
            let c = ca[i];
            while (c.charAt(0) === ' ') {
                c = c.substring(1);
            }
            if (c.indexOf(name) === 0) {
                return c.substring(name.length, c.length);
            }
        }
        return '';
    }

    public giveConsent(): void {
        const d = new Date();
        d.setTime(d.getTime() + (356 * 24 * 60 * 60 * 1000));
        const expires = `expires=${d.toUTCString()}`;
        document.cookie = document.cookie =  `cookieConsent=1;${expires};path=/`;
    }

}
