import { browser, by, element, ExpectedConditions } from 'protractor';

export class LoginPage {

    public async navigateTo() {
        return browser.get('http://localhost:8080/login.html');
    }

    public async login(email: string, password: string) {
        await browser.wait(ExpectedConditions.presenceOf(element(by.css('#email-field'))));
        await element(by.css('#email-field')).sendKeys(email);
        await element(by.css('#password-field')).sendKeys(password);
        return element(by.css('#login-button')).click();
    }

}
