import { by, element, browser, ExpectedConditions } from 'protractor';

export class FoodPlanPage {

    public async navigateTo(): Promise<void> {
        await browser.wait(ExpectedConditions.elementToBeClickable(element(by.css('#food-plan-tab'))));
        console.debug('button should be clickable now');
        await browser.sleep(10000);
        console.debug('but i slept anyway');
        return element(by.css('#food-plan-tab')).click();
    }

    public async setMeal(day: string, meal: string): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css(`#${day}`))));
        return element(by.css(`#${day}`)).sendKeys(meal);
    }

    public async selectMealFromCookbook(meal: string): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.linkText(meal))));
        return element(by.linkText(meal)).click();
    }

    public async selectIngredient(index: number): Promise<void> {
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css(`#ingredient-${index}`))));
        return element(by.css(`#ingredient-${index}`)).click();
    }

    public async saveIngredients(): Promise<void> {
        return element(by.css('#save-ingredients-button')).click();
    }

    public async clear(): Promise<void> {
        return element(by.css('#clear-button')).click();
    }

    public async isFoodPlanEmpty(): Promise<boolean> {
        return Promise.all([
            element(by.css('#monday')).getText().then((text: string) => text.length === 0),
            element(by.css('#tuesday')).getText().then((text: string) => text.length === 0),
            element(by.css('#wednesday')).getText().then((text: string) => text.length === 0),
            element(by.css('#thursday')).getText().then((text: string) => text.length === 0),
            element(by.css('#friday')).getText().then((text: string) => text.length === 0),
            element(by.css('#saturday')).getText().then((text: string) => text.length === 0),
            element(by.css('#sunday')).getText().then((text: string) => text.length === 0)
        ])
        .then((all: Array<boolean>) => all.every((value: boolean) => value === true));
    }
}
