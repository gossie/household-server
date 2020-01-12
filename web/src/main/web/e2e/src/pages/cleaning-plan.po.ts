import { element, by } from 'protractor';

export default class CleaningPlanPage {

    public async navigateTo() {
        return element(by.css('#cleaning-plan-tab')).click();
    }

    public async addChore(name: string, interval: number) {
        await element(by.css('#chore-name')).sendKeys(name);
        await element(by.css('#chore-interval')).sendKeys(interval);
        return element(by.css('add-chore-button')).click();
    }

    public async getNumberOfChores() {
        return element.all(by.css('.card-header')).count();
    }

    public async deleteChore(index: number) {
        await element.all(by.css(`.card-header:nth-of-type(${index})`)).click();
        return element(by.css('#delete-button')).click();
    }

}