import { by, element } from "protractor";

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
        return element(by.css(`#group-${group} h5.toggle`)).click();
    }

}
