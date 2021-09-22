import { by, element, browser, ExpectedConditions } from 'protractor';

export class ShoppingListPage {

    public navigateTo() {
        return element(by.css('#shopping-list-tab')).click();
    }

    public async addGroup(group: string): Promise<void> {
        await element(by.css('#group-field')).sendKeys(group);
        return element(by.css('#add-group-button')).click();
    }

    public async deleteGroup(group: string): Promise<void> {
        return element(by.css(`#delete-group-button-${group}`)).click();
    }

    public async getNumberOfGroups(): Promise<number> {
        return element.all(by.css(`.group`)).count();
    }

    public async addItemToGroup(group: string, item: string): Promise<void> {
        await element(by.css(`#item-field-${group}`)).sendKeys(item);
        return element(by.css(`#add-item-button-${group}`)).click();
    }

    public async changeItem(group: string, currentItemName: string, newItemName: string): Promise<void> {
        const editButton = element(by.css(`#group-${group}`))
            .element(by.cssContainingText('.item', currentItemName))
            .element(by.css('#edit-mode-button'));
        await browser.wait(ExpectedConditions.elementToBeClickable(editButton));
        await editButton.click();

        await element(by.css(`#group-${group}`))
            .element(by.css('#edit-field'))
            .clear();
        await element(by.css(`#group-${group}`))
            .element(by.css('#edit-field'))
            .sendKeys(newItemName);

        return element(by.css(`#group-${group}`))
            .element(by.css('#save-button'))
            .click();
    }

    public async selectItem(group: string, item: string): Promise<void> {
        console.debug(`selecting ${item} in group ${group}`);
        const el = element(by.css(`#group-${group}`))
            .element(by.cssContainingText('.item', item))
            .element(by.css('app-checkbox'));
        console.debug(`waiting for ${item} in group ${group} to be clickable`);
        await browser.wait(ExpectedConditions.elementToBeClickable(el));
        console.debug(`clicking ${item} in group ${group} to be clickable`);
        return el.click();
    }

    public async clear(group: string): Promise<void> {
        console.debug(`clearing group ${group}`);
        await browser.wait(ExpectedConditions.elementToBeClickable(element(by.css(`#clear-group-button-${group}`))));
        return element(by.css(`#clear-group-button-${group}`)).click();
    }

    public async getNumberOfItems(group: string): Promise<number> {
        return element.all(by.css(`#group-${group} .item`)).count();
    }

    public async toggleGroup(group: string): Promise<void> {
        return element(by.css(`#group-${group} h5.toggle`)).click();
    }

}
