import { browser, by, element, ExpectedConditions } from 'protractor';

export class NoHouseholdPage {

    public async createHousehold() {
        await browser.wait(ExpectedConditions.elementToBeClickable(element(by.css('#create-household-button'))));
        return element(by.css('#create-household-button')).click();
    }

}