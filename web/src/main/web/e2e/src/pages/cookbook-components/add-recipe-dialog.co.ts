import { by, element } from 'protractor';

export class AddRecipeDialog {

    public async navigateTo() {
        return element(by.css('#add-recipe-button')).click();
    }

    public async setRecipeName(name: string) {
        return element(by.css('#recipe-name-field')).sendKeys(name);
    }

    public async addIngrediant(amount: number, unit: string, name: string) {
        await element(by.css('#amount-field')).sendKeys(amount);
        await element(by.css('#unit-field')).sendKeys(unit);
        await element(by.css('#name-field')).sendKeys(name);
        return element(by.css('#add-ingredient-button')).click();
    }

    public async saveRecipe() {
        return element(by.css('#save-recipe-button')).click();
    }

}
