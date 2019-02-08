import { browser, by, element } from "protractor";

export class LoginPage {

    public async navigateTo() {
        await browser.get('/');
        await browser.driver.manage().window().maximize();
        return element(by.css('#login-tab')).click();
    }

    public async login(email: string, password: string) {
        await element(by.css('#email-field')).sendKeys(email);
        await element(by.css('#password-field')).sendKeys(password);
        return element(by.css('#login-button')).click();
    }

}
