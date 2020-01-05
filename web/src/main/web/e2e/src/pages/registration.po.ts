import { browser, by, element } from 'protractor';

export class RegistrationPage {

    public async navigateTo() {
        await browser.get('http://localhost:5000/registration.html');
        return browser.driver.manage().window().maximize();
    }

    public async register(email: string, password: string) {
        try {
            await element(by.css('#consent-button')).click();
        } catch (e) {}
        await element(by.css('#email-field')).sendKeys(email);
        await element(by.css('#password-field')).sendKeys(password);
        await element(by.css('#password-repeat-field')).sendKeys(password);
        await element(by.css('#data-protection')).click();
        return element(by.css('#register-button')).click();
    }

}
