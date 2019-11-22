import { by, element, browser, ExpectedConditions } from 'protractor';

export class ShoppingListPage {

    public async navigateTo(): Promise<void> {
        await browser.wait(ExpectedConditions.elementToBeClickable(element(by.css('#shopping-list-tab'))));
        console.debug('button should be clickable now');
        await browser.sleep(10000);
        console.debug('but i slept anyway');
        return element(by.css('#shopping-list-tab')).click();
    }

    public async addGroup(group: string): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css('#group-field'))));
        await element(by.css('#group-field')).sendKeys(group);
        return element(by.css('#add-group-button')).click();
    }

    public async deleteGroup(group: string): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css(`#delete-group-button-${group}`))));
        return element(by.css(`#delete-group-button-${group}`)).click();
    }

    public async getNumberOfGroups(): Promise<number> {
        return element.all(by.css(`.group`)).count();
    }

    public async addItemToGroup(group: string, item: string): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css(`#item-field-${group}`))));
        await element(by.css(`#item-field-${group}`)).sendKeys(item);
        return element(by.css(`#add-item-button-${group}`)).click();
    }

    public async selectItem(group: string, item: string): Promise<void> {
        return element(by.css(`#group-${group}`)).element(by.cssContainingText('.item', item)).click();
    }

    public async clear(group: string): Promise<void> {
        return element(by.css(`#clear-group-button-${group}`)).click();
    }

    public async getNumberOfItems(group: string): Promise<number> {
        return element.all(by.css(`#group-${group} .item`)).count();
    }

    public async toggleGroup(group: string): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css(`#group-${group} h5.toggle`))));
        return element(by.css(`#group-${group} h5.toggle`)).click();
    }

}