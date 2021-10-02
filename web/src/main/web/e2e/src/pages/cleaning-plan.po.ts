import { element, by, browser, ExpectedConditions } from 'protractor';

export default class CleaningPlanPage {

    public async navigateTo() {
        return element(by.css('#cleaning-plan-tab')).click();
    }

    public async addChore(name: string, interval: number) {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css('#chore-name'))));
        await element(by.css('#chore-name')).sendKeys(name);
        await element(by.css('#chore-interval')).sendKeys(interval);
        return element(by.css('#add-chore-button')).click();
    }

    public async getNumberOfChores() {
        return element.all(by.css('.card-header')).count();
    }

    public async deleteChore(index: number) {
        const chore = element.all(by.css('.card-header')).first();
        await browser.wait(ExpectedConditions.elementToBeClickable(chore));
        await chore.click();

        const deleteButton = element(by.css('#delete-button'));
        await browser.wait(ExpectedConditions.elementToBeClickable(deleteButton));
        return deleteButton.click();
    }

}