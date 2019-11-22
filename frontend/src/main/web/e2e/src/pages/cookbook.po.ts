import { by, element, ExpectedConditions, browser } from 'protractor';
import { AddRecipeDialog } from './cookbook-components/add-recipe-dialog.co';

export class CookbookPage {

    public async navigateTo(): Promise<void> {
        await browser.wait(ExpectedConditions.elementToBeClickable(element(by.css('#cookbook-tab'))));
        console.debug('button should be clickable now');
        await browser.sleep(10000);
        console.debug('but i slept anyway');
        return element(by.css('#cookbook-tab')).click();
    }

    public async openAddRecipeDialog(): Promise<AddRecipeDialog> {
        const addRecipeDialog: AddRecipeDialog = new AddRecipeDialog();
        await addRecipeDialog.navigateTo();
        return new Promise((resolve, _) => {
            resolve(addRecipeDialog);
        });
    }

}
