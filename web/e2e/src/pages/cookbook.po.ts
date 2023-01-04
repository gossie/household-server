import { by, element } from 'protractor';
import { AddRecipeDialog } from './cookbook-components/add-recipe-dialog.co';

export class CookbookPage {

    public async navigateTo() {
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
