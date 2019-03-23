import { LoginPage } from "../pages/login.po";
import { ShoppingListPage } from "../pages/shopping-list.po";
import { FoodPlanPage } from "../pages/food-plan.po";
import { browser, by, element, ExpectedConditions } from "protractor";

describe('food plan', () => {
    const loginPage: LoginPage = new LoginPage();
    const foodPlanPage: FoodPlanPage = new FoodPlanPage();
    const shoppingListPage: ShoppingListPage = new ShoppingListPage();

    it('should login', async () => {
        await loginPage.navigateTo();
        await loginPage.login('neuer1@user.de', 'neuer1@user.de');
    });

    it('should open food plan', async () => {
        await foodPlanPage.navigateTo();
    });

    it('should search for a meal', async () => {
        await foodPlanPage.setMeal('monday', 'Ch');
    });

    it('should select a meal', async () => {
        await foodPlanPage.selectMealFromCookbook('Chili con carne');
    });

    it('should add some ingredients', async () => {
        await foodPlanPage.selectIngredient(0);
        await foodPlanPage.selectIngredient(2);
        await foodPlanPage.selectIngredient(5);
        await foodPlanPage.saveIngredients();
    });

    it('should go shopping', async () => {
        await shoppingListPage.navigateTo();
        expect(await shoppingListPage.getNumberOfItems('Global')).toBe(3);

        await shoppingListPage.selectItem('Global', 'Zwiebel');
        await shoppingListPage.selectItem('Global', 'Hack');
        await shoppingListPage.selectItem('Global', 'Tomatenmark');
        await shoppingListPage.clear('Global');
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css('.undo-hint'))));
        await browser.wait(ExpectedConditions.invisibilityOf(element(by.css('.undo-hint'))));
        expect(await shoppingListPage.getNumberOfItems('Global')).toBe(0);
    });

    it('should clear food plan', async () => {
        await foodPlanPage.navigateTo();
        await foodPlanPage.clear();
        await browser.wait(ExpectedConditions.visibilityOf(element(by.css('.undo-hint'))));
        await browser.wait(ExpectedConditions.invisibilityOf(element(by.css('.undo-hint'))));
        expect(await foodPlanPage.isFoodPlanEmpty()).toBeTruthy();
    })

});
