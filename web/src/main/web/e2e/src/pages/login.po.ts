import { browser, by, element, ExpectedConditions } from 'protractor';

export class LoginPage {

    public async navigateTo() {
        await browser.get('http://localhost:5000/login.html');
        return browser.driver.manage().window().maximize();
    }

    public async login(email: string, password: string) {
        await browser.wait(ExpectedConditions.presenceOf(element(by.css('#email-field'))));
        await element(by.css('#email-field')).sendKeys(email);
        await element(by.css('#password-field')).sendKeys(password);
        return element(by.css('#login-button')).click();
    }

}
