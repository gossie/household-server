import { browser, by, element } from "protractor";

export class SplashPage {

    public navigateTo() {
        return browser.get('/');
    }

    public async login(email: string, password: string): Promise<void> {
        await element(by.css('#login-tab')).click();
        await browser.waitForAngular();

        await element(by.css('#email-field')).sendKeys(email);
        await element(by.css('#password-field')).sendKeys(password);
        await element(by.css('#login-button')).click();
        await browser.waitForAngular();
    }

}
