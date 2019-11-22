import { browser, by, element, ExpectedConditions } from 'protractor';

export class NoHouseholdPage {

    public async createHousehold() {
        await browser.wait(ExpectedConditions.elementToBeClickable(element(by.css('#create-household-button'))));
        console.debug('button should be clickable now');
        await browser.sleep(10000);
        console.debug('but i slept anyway');
        return element(by.css('#create-household-button')).click();
    }

}
